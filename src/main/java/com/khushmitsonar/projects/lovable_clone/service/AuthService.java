package com.khushmitsonar.projects.lovable_clone.service;

import com.khushmitsonar.projects.lovable_clone.dto.auth.AuthResponse;
import com.khushmitsonar.projects.lovable_clone.dto.auth.LoginRequest;
import com.khushmitsonar.projects.lovable_clone.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
