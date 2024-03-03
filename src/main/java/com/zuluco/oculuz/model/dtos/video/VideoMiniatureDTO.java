package com.zuluco.oculuz.model.dtos.video;

import lombok.Data;
@Data
public class VideoMiniatureDTO {
    private Long id;
    private String previewUrl;
    private String channelAvatarUrl;
    private String videoUrl;
    private String title;
    private String channelName;
    private String duration;
    private String uploadDate;
    private Integer views;
}
