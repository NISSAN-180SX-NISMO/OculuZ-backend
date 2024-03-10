package com.zuluco.oculuz.services;

import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.intermediates.Subscription;
import com.zuluco.oculuz.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Boolean isUserSubscribedToChannel(User user, Channel channel) {
        return subscriptionRepository.findByUserAndChannel(user, channel).isPresent();
    }
}
