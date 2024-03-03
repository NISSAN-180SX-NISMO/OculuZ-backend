package com.zuluco.oculuz.model.dtos;

import com.zuluco.oculuz.model.dtos.video.VideoMiniatureDTO;
import com.zuluco.oculuz.model.dtos.video.VideoPageDTO;
import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.entities.Video;
import com.zuluco.oculuz.model.entities.associations.markValue;

public class ModelMapper {
    public static VideoPageDTO toVideoPageDTO(Video video) {
        VideoPageDTO videoPageDTO = new VideoPageDTO();
        videoPageDTO.setId(video.getId());
        videoPageDTO.setChannelId(video.getChannel().getId());
        videoPageDTO.setUrl(video.getUrl());
        videoPageDTO.setPreviewUrl(video.getPreviewUrl());
        videoPageDTO.setChannelAvatarUrl(video.getChannel().getAvatarUrl());
        videoPageDTO.setTitle(video.getTitle());
        videoPageDTO.setChannelName(video.getChannel().getName());
        videoPageDTO.setDescription(video.getDescription());
        videoPageDTO.setDuration(video.getDuration().toString());
        videoPageDTO.setUploadDate(video.getUploadDate().toString());
        videoPageDTO.setEditDate(video.getEditDate().toString());
        videoPageDTO.setMonetized(video.isMonetized());
        videoPageDTO.setAdultContent(video.isAdultContent());
        videoPageDTO.setBanned(video.isBanned());
        videoPageDTO.setViews(video.getViews().size());
        videoPageDTO.setLikes(video.getMarks().stream().filter(mark -> mark.getValue().equals(markValue.LIKE)).toArray().length);
        videoPageDTO.setDislikes(video.getMarks().stream().filter(mark -> mark.getValue().equals(markValue.DISLIKE)).toArray().length);
        videoPageDTO.setCommentBranchId(video.getCommentBranch().getId());
        return videoPageDTO;
    }

    public static VideoMiniatureDTO toVideoMiniatureDTO(Video video) {
        VideoMiniatureDTO videoMiniatureDTO = new VideoMiniatureDTO();
        videoMiniatureDTO.setId(video.getId());
        videoMiniatureDTO.setPreviewUrl(video.getPreviewUrl());
        videoMiniatureDTO.setChannelAvatarUrl(video.getChannel().getAvatarUrl());
        videoMiniatureDTO.setVideoUrl(video.getUrl());
        videoMiniatureDTO.setTitle(video.getTitle());
        videoMiniatureDTO.setChannelName(video.getChannel().getName());
        videoMiniatureDTO.setDuration(video.getDuration().toString());
        videoMiniatureDTO.setUploadDate(video.getUploadDate().toString());
        videoMiniatureDTO.setViews(video.getViews().size());
        return videoMiniatureDTO;
    }

    public static User toUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        return user;
    }
}
