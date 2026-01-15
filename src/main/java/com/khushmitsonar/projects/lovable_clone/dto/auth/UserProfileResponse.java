package com.khushmitsonar.projects.lovable_clone.dto.auth;

public record UserProfileResponse(
        Long id,
        String username,
        String name,
        String avatarUrl
) {
}
