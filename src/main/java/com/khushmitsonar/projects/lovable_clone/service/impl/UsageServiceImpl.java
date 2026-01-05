package com.khushmitsonar.projects.lovable_clone.service.impl;

import com.khushmitsonar.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.khushmitsonar.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import com.khushmitsonar.projects.lovable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
