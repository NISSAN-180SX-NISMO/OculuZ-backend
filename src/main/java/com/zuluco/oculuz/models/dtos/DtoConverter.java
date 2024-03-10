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
        ChannelPageDTO dto = new ChannelPageDTO();
        dto.setName(channel.getName() != null ? channel.getName() : "");
        dto.setRegistDate(channel.getRegistDate() != null ? channel.getRegistDate() : null);
        dto.setDescription(channel.getDescription() != null ? channel.getDescription() : "");
        dto.setAvatarUrl(channel.getAvatarUrl() != null ? channel.getAvatarUrl() : "");
        dto.setHeaderUrl(channel.getHeaderUrl() != null ? channel.getHeaderUrl() : "");
        dto.setAuthorUsername(channel.getAuthor() != null && channel.getAuthor().getUsername() != null ? channel.getAuthor().getUsername() : "");
        dto.setAuthorAvatarUrl(channel.getAuthor() != null && channel.getAuthor().getAvatarUrl() != null ? channel.getAuthor().getAvatarUrl() : "");
        dto.setSubscribersCount(channel.getSubscribers() != null ? channel.getSubscribers().size() : 0);
        dto.setVideosCount(channel.getVideos() != null ? channel.getVideos().size() : 0);
        dto.setIsSubscribed(null);
        return dto;
    }
}
