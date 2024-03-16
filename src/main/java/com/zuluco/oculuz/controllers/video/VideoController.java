package com.zuluco.oculuz.controllers.video;

import com.amazonaws.services.kms.model.NotFoundException;
import com.zuluco.oculuz.models.dtos.video.VideoPreviewDTO;
import com.zuluco.oculuz.payload.response.MessageResponse;
import com.zuluco.oculuz.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/{videoId}")
    public ResponseEntity<?> getVideoById(@PathVariable String videoId) {
        try {
            return ResponseEntity.ok(videoService.getVideoPageById(videoId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<VideoPreviewDTO>> getAllVideos() {
        try {
            return ResponseEntity.ok(videoService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
