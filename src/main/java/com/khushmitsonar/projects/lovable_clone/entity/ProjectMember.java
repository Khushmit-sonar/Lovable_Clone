package com.khushmitsonar.projects.lovable_clone.entity;

import com.khushmitsonar.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public class ProjectMember {

    ProjectMemberId id;

    Project project;

    User user;

    ProjectRole projectRole;

    Instant invitedAt;
    Instant acceptedAt;
}
