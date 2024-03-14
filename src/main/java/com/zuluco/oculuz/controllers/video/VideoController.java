package com.zuluco.oculuz.controllers.video;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.*;
import com.zuluco.oculuz.payload.request.InitiateUploadRequest;
import com.zuluco.oculuz.payload.response.CompleteUploadResponse;
import com.zuluco.oculuz.payload.response.UploadResponse;
import com.zuluco.oculuz.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @PostMapping("/init-upload")
    public ResponseEntity<UploadResponse> initUpload(@RequestBody InitiateUploadRequest request) {
        try {
            String uploadId = videoService.initUpload(request.getFileName()).getUploadId();
            logger.info("Upload initiated with ID: " + uploadId);
            return ResponseEntity.ok(new UploadResponse(uploadId, "Upload initiated successfully", 0));
        } catch (AmazonServiceException e) {
            logger.error("Error occurred while initiating upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResponse(null, "Error occurred while initiating upload", 0));
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("partNumber") int partNumber,
            @RequestParam("uploadId") String uploadId
    ) {
        try {
            UploadPartResult uploadResult = videoService.handleFileUpload(file, partNumber, uploadId);
            logger.info("Part " + partNumber + " uploaded successfully");
            return ResponseEntity.ok(new UploadResponse(uploadId, "Part " + partNumber + " uploaded successfully", partNumber));
        } catch (IOException e) {
            logger.error("Error occurred while uploading part " + partNumber, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResponse(uploadId, "Error occurred while uploading part " + partNumber, partNumber));
        }
    }

    @PostMapping("/complete-upload")
    public ResponseEntity<CompleteUploadResponse> completeMultipartUpload(@RequestParam("uploadId") String uploadId) {
        try {
            CompleteMultipartUploadResult compResult = videoService.completeMultipartUpload(uploadId);
            logger.info("Загрузка завершена. Файл доступен по ключу: " + compResult.getKey());
            return ResponseEntity.ok(new CompleteUploadResponse(compResult.getLocation()));
        } catch (AmazonServiceException e) {
            logger.error("Error occurred while completing upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CompleteUploadResponse(null));
        }
    }
}