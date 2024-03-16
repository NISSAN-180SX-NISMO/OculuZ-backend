package com.zuluco.oculuz.controllers.channel;


import com.amazonaws.Response;
import com.zuluco.oculuz.models.dtos.video.NewVideoDTO;
import com.zuluco.oculuz.payload.response.MessageResponse;
import com.zuluco.oculuz.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/channel/{channelName}")
public class ChannelPageController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload-video")
    private ResponseEntity<?> uploadVideo(@PathVariable("channelName") String channelName, @RequestBody NewVideoDTO video) {
        try {
            videoService.saveNewVideo(channelName, video);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error occurred while uploading video"));
        }
    }
}
