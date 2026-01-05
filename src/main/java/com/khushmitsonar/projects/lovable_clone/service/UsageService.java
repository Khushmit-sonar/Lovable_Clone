package com.khushmitsonar.projects.lovable_clone.service;

import com.khushmitsonar.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.khushmitsonar.projects.lovable_clone.dto.subscription.UsageTodayResponse;


public interface UsageService {
     UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
