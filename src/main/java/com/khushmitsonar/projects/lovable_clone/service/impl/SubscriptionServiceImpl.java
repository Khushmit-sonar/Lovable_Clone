package com.khushmitsonar.projects.lovable_clone.service.impl;

import com.khushmitsonar.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.khushmitsonar.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.khushmitsonar.projects.lovable_clone.dto.subscription.PortalResponse;
import com.khushmitsonar.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import com.khushmitsonar.projects.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
