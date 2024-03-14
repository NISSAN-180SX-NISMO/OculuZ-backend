package com.zuluco.oculuz.services;

import com.zuluco.oculuz.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;


}
