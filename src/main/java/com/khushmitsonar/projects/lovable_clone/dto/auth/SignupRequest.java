package com.khushmitsonar.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank String username,
        @Size(min = 3, max = 30) String name,
        @Size() String password
) {
}
