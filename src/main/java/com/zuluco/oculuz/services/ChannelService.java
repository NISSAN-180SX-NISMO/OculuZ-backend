package com.zuluco.oculuz.services;

import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public Optional<Channel> getChannelByChannelName(String channelName) {
        return channelRepository.findByName(channelName);
    }


    public void createChannel(Channel channel) {
        channelRepository.save(channel);
    }
}
