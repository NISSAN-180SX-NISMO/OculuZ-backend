package com.zuluco.oculuz.models.dtos;

import com.zuluco.oculuz.models.dtos.channel.ChannelMiniatureDTO;
import com.zuluco.oculuz.models.dtos.channel.ChannelPageDTO;
import com.zuluco.oculuz.models.dtos.user.UserPageDTO;
import com.zuluco.oculuz.models.dtos.video.NewVideoDTO;
import com.zuluco.oculuz.models.dtos.video.VideoPageDTO;
import com.zuluco.oculuz.models.dtos.video.VideoPreviewDTO;
import com.zuluco.oculuz.models.entities.*;
import com.zuluco.oculuz.models.entities.intermediates.MarkType;
import com.zuluco.oculuz.services.CommentBranchService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.naming.InsufficientResourcesException;
import java.util.stream.Collectors;

@Component
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

    public static ChannelMiniatureDTO convertChannelToChannelMiniatureDto(Channel channel) {
        ChannelMiniatureDTO dto = new ChannelMiniatureDTO();
        dto.setName(channel.getName() != null ? channel.getName() : "");
        dto.setAvatarUrl(channel.getAvatarUrl() != null ? channel.getAvatarUrl() : "");
        dto.setSubscribersCount(channel.getSubscribers() != null ? channel.getSubscribers().size() : 0);
        return dto;
    }

    public static Video convertNewVideoDtoToVideo(NewVideoDTO newVideoDto) {
        Video newVideo = new Video();
        newVideo.setTitle(newVideoDto.getTitle());
        newVideo.setUrl(newVideoDto.getUrl());
        newVideo.setDescription(newVideoDto.getDescription());
        newVideo.setDuration(newVideoDto.getDuration());
        newVideo.setPreviewUrl(newVideoDto.getPreviewUrl());
        newVideo.setUploadDate(newVideoDto.getUploadDate());
        newVideo.setEditDate(null);
        newVideo.setMonetized(!newVideoDto.isAdultContent());
        newVideo.setAdultContent(newVideoDto.isAdultContent());
        newVideo.setBanned(false);
        return newVideo;
    }

    public static VideoPageDTO convertVideoToVideoPageDto(Video video) {
        VideoPageDTO dto = new VideoPageDTO();
        dto.setId(video.getId() != null ? video.getId() : "");
        dto.setUrl(video.getUrl() != null ? video.getUrl() : "");
        dto.setPreviewUrl(video.getPreviewUrl() != null ? video.getPreviewUrl() : "");
        dto.setChannelAvatarUrl(video.getChannel() != null && video.getChannel().getAvatarUrl() != null ? video.getChannel().getAvatarUrl() : "");
        dto.setTitle(video.getTitle() != null ? video.getTitle() : "");
        dto.setChannelName(video.getChannel() != null && video.getChannel().getName() != null ? video.getChannel().getName() : "");
        dto.setDescription(video.getDescription() != null ? video.getDescription() : "");
        dto.setDuration(video.getDuration() != null ? video.getDuration() : null);
        dto.setUploadDate(video.getUploadDate() != null ? video.getUploadDate() : null);
        dto.setEditDate(video.getEditDate() != null ? video.getEditDate() : null);
        dto.setMonetized(video.isMonetized());
        dto.setAdultContent(video.isAdultContent());
        dto.setBanned(video.isBanned());
        dto.setViews(video.getViews() != null ? video.getViews().size() : 0);
        Integer likesCount = Math.toIntExact(video.getMarks() != null ?
                video.getMarks().stream().filter(mark -> mark.getValue() == MarkType.LIKE).count() : 0);
        dto.setLikes(likesCount);
        Integer dislikesCount = Math.toIntExact(video.getMarks() != null ?
                video.getMarks().stream().filter(mark -> mark.getValue() == MarkType.DISLIKE).count() : 0);
        dto.setDislikes(dislikesCount);
        dto.setCommentBranchId(video.getCommentBranch() != null ? video.getCommentBranch().getId() : null);
        return dto;
    }

    public static VideoPreviewDTO convertVideoToVideoPreviewDto(Video video) {
        VideoPreviewDTO dto = new VideoPreviewDTO();
        dto.setId(video.getId() != null ? video.getId() : "");
        dto.setPreviewUrl(video.getPreviewUrl() != null ? video.getPreviewUrl() : "");
        dto.setChannelAvatarUrl(video.getChannel() != null && video.getChannel().getAvatarUrl() != null ? video.getChannel().getAvatarUrl() : "");
        dto.setUrl(video.getUrl() != null ? video.getUrl() : "");
        dto.setTitle(video.getTitle() != null ? video.getTitle() : "");
        dto.setChannelName(video.getChannel() != null && video.getChannel().getName() != null ? video.getChannel().getName() : "");
        dto.setDuration(video.getDuration() != null ? video.getDuration() : null);
        dto.setUploadDate(video.getUploadDate() != null ? video.getUploadDate() : null);
        dto.setViews(video.getViews() != null ? video.getViews().size() : 0);
        return dto;
    }
}
