package com.zuluco.oculuz.services;

import com.amazonaws.services.kms.model.NotFoundException;
import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.repository.ChannelRepository;
import com.zuluco.oculuz.repository.SubscriptionRepository;
import com.zuluco.oculuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Channel> getChannelByChannelName(String channelName) {
        return channelRepository.findByName(channelName);
    }


    public void createChannel(Channel channel) {
        channelRepository.save(channel);
    }

    @Transactional
    public Boolean userIsSubscribed(String username, String channelName) throws UsernameNotFoundException, NotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username)
        );
        Channel channel = channelRepository.findByName(channelName).orElseThrow(
                () -> new NotFoundException("Channel Not Found with name: " + channelName)
        );
        return subscriptionRepository.existsByUserAndChannel(user, channel);
    }
}
