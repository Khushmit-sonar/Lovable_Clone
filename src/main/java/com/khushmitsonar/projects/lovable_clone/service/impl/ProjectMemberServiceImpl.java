package com.khushmitsonar.projects.lovable_clone.service.impl;

import com.khushmitsonar.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.khushmitsonar.projects.lovable_clone.dto.member.MemberResponse;
import com.khushmitsonar.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.khushmitsonar.projects.lovable_clone.entity.Project;
import com.khushmitsonar.projects.lovable_clone.entity.ProjectMember;
import com.khushmitsonar.projects.lovable_clone.entity.ProjectMemberId;
import com.khushmitsonar.projects.lovable_clone.entity.User;
import com.khushmitsonar.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.khushmitsonar.projects.lovable_clone.repository.ProjectMemberRepository;
import com.khushmitsonar.projects.lovable_clone.repository.ProjectRepository;
import com.khushmitsonar.projects.lovable_clone.repository.UserRepository;
import com.khushmitsonar.projects.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        return projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponsesFromMember)
                .toList();


    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if(!invitee.getId().equals(userId)){
            throw new RuntimeException("Cannot invite yourself");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite once again");
        }
        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponsesFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);


        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponsesFromMember(projectMember);
    }

    @Override
    public void deleteProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Member Not Found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);

    }

    //Internal Function
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }

}
