package com.zuluco.oculuz.services;

import com.amazonaws.services.kms.model.NotFoundException;
import com.zuluco.oculuz.models.entities.Comment;
import com.zuluco.oculuz.models.entities.CommentBranch;
import com.zuluco.oculuz.repository.CommentBranchRepository;
import com.zuluco.oculuz.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommentBranchService {
    @Autowired
    private CommentBranchRepository commentBranchRepository;

    @Autowired
    private VideoRepository videoRepository;

    public void createCommentBranch(String sourceVideoId) {
        CommentBranch commentBranch = new CommentBranch();
        commentBranch.setVideo(videoRepository.getVideoById(sourceVideoId).orElseThrow(
                () -> new NotFoundException("Video with id " + sourceVideoId + " not found.")
        ));
        commentBranch.setComments(new ArrayList<Comment>());
        commentBranchRepository.save(commentBranch);
    }
}
