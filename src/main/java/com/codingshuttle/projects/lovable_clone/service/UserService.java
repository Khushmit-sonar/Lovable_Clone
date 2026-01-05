package com.codingshuttle.projects.lovable_clone.service;

import com.codingshuttle.projects.lovable_clone.dto.auth.UserProfileResponse;


public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
