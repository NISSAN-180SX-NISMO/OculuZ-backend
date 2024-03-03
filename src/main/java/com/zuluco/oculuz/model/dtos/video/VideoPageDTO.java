package com.zuluco.oculuz.model.dtos.video;

import lombok.Data;
@Data
public class VideoPageDTO {
    private Long id;
    private Long channelId;
    private String url;
    private String previewUrl;
    private String channelAvatarUrl;
    private String title;
    private String channelName;
    private String description;
    private String duration;
    private String uploadDate;
    private String editDate;
    private Boolean monetized;
    private Boolean adultContent;
    private Boolean banned;
    private Integer views;
    private Integer likes;
    private Integer dislikes;
    private Long commentBranchId;
}