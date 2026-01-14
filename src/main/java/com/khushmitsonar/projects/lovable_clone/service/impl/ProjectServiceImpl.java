package com.khushmitsonar.projects.lovable_clone.service.impl;

import com.khushmitsonar.projects.lovable_clone.dto.project.ProjectRequest;
import com.khushmitsonar.projects.lovable_clone.dto.project.ProjectResponse;
import com.khushmitsonar.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.khushmitsonar.projects.lovable_clone.entity.Project;
import com.khushmitsonar.projects.lovable_clone.entity.User;
import com.khushmitsonar.projects.lovable_clone.error.ResourceNotFoundException;
import com.khushmitsonar.projects.lovable_clone.mapper.ProjectMapper;
import com.khushmitsonar.projects.lovable_clone.repository.ProjectRepository;
import com.khushmitsonar.projects.lovable_clone.repository.UserRepository;
import com.khushmitsonar.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);

    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(project -> projectMapper.toProjectSummaryResponse(project))
//                .collect(Collectors.toList());

        List<Project> projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project  = getAccessibleProjectById(id , userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project  = getAccessibleProjectById(id , userId);
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project =  getAccessibleProjectById(id , userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete");

        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    //INTERNAL FUNCTION
    //why we created this function because we were repeating
    // Project project =  projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    //to we created this function to respect dry principle (do repeat not)
    public Project getAccessibleProjectById(Long projectId , Long userId){
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("project" , projectId.toString() ));
    }
}
