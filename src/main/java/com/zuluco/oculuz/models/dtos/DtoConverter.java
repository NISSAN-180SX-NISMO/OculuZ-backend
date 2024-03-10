package com.zuluco.oculuz.models.dtos;

import com.zuluco.oculuz.models.dtos.channel.ChannelPageDTO;
import com.zuluco.oculuz.models.dtos.user.UserPageDTO;
import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.stream.Collectors;

public class DtoConverter {

    public static UserPageDTO convertUserToUserPageDto(User user) {
        UserPageDTO dto = new UserPageDTO();
        dto.setId(user.getId() != null ? user.getId() : 0);
        dto.setUsername(user.getUsername() != null ? user.getUsername() : "");
        dto.setEmail(user.getEmail() != null ? user.getEmail() : "");
        dto.setBanned(user.getBanned() != null ? user.getBanned() : false);
        dto.setBanEndDate(user.getBanEndDate() != null ? user.getBanEndDate() : null);
        dto.setBirthDate(user.getBirthDate() != null ? user.getBirthDate() : null);
        dto.setRegistDate(user.getRegistDate() != null ? user.getRegistDate() : null);
        dto.setAvatarUrl(user.getAvatarUrl() != null ? user.getAvatarUrl() : "");
        dto.setCountry(user.getCountry() != null ? user.getCountry().getName() : "");
        dto.setRoles(user.getRoles().stream().map(Role::toString).collect(Collectors.toList()));
        return dto;
    }

    public static ChannelPageDTO convertChannelToChannelPageDto(Channel channel) {
        ModelMapper modelMapper = new ModelMapper();
        ChannelPageDTO dto = modelMapper.map(channel, ChannelPageDTO.class);

        dto.setSubscribersCount(channel.getSubscribers().size());
        dto.setVideosCount(channel.getVideos().size());

        return dto;
    }
}
