package com.zuluco.oculuz.controllers;

import com.zuluco.oculuz.model.dtos.ModelMapper;
import com.zuluco.oculuz.model.dtos.video.VideoMiniatureDTO;
import com.zuluco.oculuz.model.dtos.video.VideoPageDTO;
import com.zuluco.oculuz.model.entities.Video;
import com.zuluco.oculuz.model.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/saveVideo", method = RequestMethod.POST)
    public ResponseEntity<String> saveVideo(@RequestBody VideoPageDTO video) {
        videoService.saveVideo(new Video());
        return ResponseEntity.status(200).body("Video saved");
    }

    @RequestMapping(value = "/getVideoList", method = RequestMethod.GET)
    public ResponseEntity<List<VideoMiniatureDTO>> getVideos() {
        List<VideoMiniatureDTO> videoMiniatureDTOs = videoService.getVideos().stream()
                .map(ModelMapper::toVideoMiniatureDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(videoMiniatureDTOs);
    }


}
