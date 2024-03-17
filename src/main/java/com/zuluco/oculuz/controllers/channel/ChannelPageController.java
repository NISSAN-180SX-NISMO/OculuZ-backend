package com.zuluco.oculuz.controllers.channel;


import com.amazonaws.Response;
import com.amazonaws.services.kms.model.NotFoundException;
import com.zuluco.oculuz.models.dtos.video.NewVideoDTO;
import com.zuluco.oculuz.payload.response.MessageResponse;
import com.zuluco.oculuz.services.ChannelService;
import com.zuluco.oculuz.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/channel/{channelName}")
public class ChannelPageController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChannelService channelService;

    @PostMapping("/upload-video")
    private ResponseEntity<?> uploadVideo(@PathVariable("channelName") String channelName, @RequestBody NewVideoDTO video) {
        try {
            videoService.saveNewVideo(channelName, video);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error occurred while uploading video"));
        }
    }

    @GetMapping("/check-subscription")
    private ResponseEntity<?> checkSubscription(@PathVariable("channelName") String channelName) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String username;

            if (principal instanceof UserDetails)
                username = ((UserDetails)principal).getUsername();
            else
                username = null;

            if (channelService.userIsSubscribed(username, channelName))
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (UsernameNotFoundException | NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
