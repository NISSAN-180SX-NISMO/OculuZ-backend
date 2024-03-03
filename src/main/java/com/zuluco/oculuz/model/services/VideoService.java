package com.zuluco.oculuz.model.services;

import com.zuluco.oculuz.model.entities.Video;
import com.zuluco.oculuz.model.repos.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class VideoService {
    @Autowired
    private VideoRepo videoRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveVideo(Video video) {
        videoRepo.save(video);
    }
    @Transactional
    public ArrayList<Video> getVideos() {
        ArrayList<Video> videos = new ArrayList<>();
        videoRepo.findAll().forEach(videos::add);
        return videos;
    }
}
