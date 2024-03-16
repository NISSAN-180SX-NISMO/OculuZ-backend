package com.zuluco.oculuz.services;

import com.amazonaws.services.kms.model.NotFoundException;
import com.zuluco.oculuz.models.dtos.DtoConverter;
import com.zuluco.oculuz.models.dtos.video.NewVideoDTO;
import com.zuluco.oculuz.models.entities.Video;
import com.zuluco.oculuz.models.entities.intermediates.Mark;
import com.zuluco.oculuz.models.entities.intermediates.View;
import com.zuluco.oculuz.repository.ChannelRepository;
import com.zuluco.oculuz.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CommentBranchService commentBranchService;

    @Autowired
    private ChannelRepository channelRepository;

    @Transactional
    public void saveNewVideo(String channelName, NewVideoDTO newVideoDTO) {
        logger.info("Start saveNewVideo method with channelName: {} and newVideoDTO: {}", channelName, newVideoDTO);

        Video video = DtoConverter.convertNewVideoDtoToVideo(newVideoDTO);

        video.setChannel(channelRepository.findByName(channelName).orElseThrow(
                () -> new NotFoundException("Channel with name " + channelName + " not found.")
        ));

        video.setMarks(new ArrayList<Mark>());
        video.setViews(new ArrayList<View>());

        videoRepository.save(video);
        logger.info("Video saved in database");

        commentBranchService.createCommentBranch(video.getId());
        logger.info("Comment branch created for video \"{}\" with id: {}",video.getTitle(), video.getId());

        logger.info("Video saved successfully!");
    }
}
