package com.zuluco.oculuz.controllers.video;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private AmazonS3 s3Client;

    private static final String bucketName = "fd22a2a2-oculuz-media-storage";
    private static String keyName = "video";

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    List<PartETag> partETags = new ArrayList<>();


    @PostMapping("/init-upload")
    public ResponseEntity<UploadResponse> initUpload(@RequestBody InitiateUploadRequest request) {
        try {
            keyName += "/" + request.getFileName();
            InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, keyName);
            InitiateMultipartUploadResult initiateMultipartUploadResult = s3Client.initiateMultipartUpload(initiateMultipartUploadRequest);

            String uploadId = initiateMultipartUploadResult.getUploadId();


            logger.info("Upload initiated with ID: " + uploadId);
            return ResponseEntity.ok(new UploadResponse(uploadId, "Upload initiated successfully", 0));
        } catch (AmazonServiceException e) {
            logger.error("Error occurred while initiating upload", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResponse(null, "Error occurred while initiating upload", 0));
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("partNumber") int partNumber, @RequestParam("uploadId") String uploadId) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());

            UploadPartRequest uploadPartRequest = new UploadPartRequest()
                    .withBucketName(bucketName)
                    .withKey(keyName) // используйте имя файла, заданное при инициализации
                    .withUploadId(uploadId)
                    .withPartNumber(partNumber)
                    .withInputStream(inputStream)
                    .withPartSize(file.getSize());


            // Загрузка части и получение результата
            UploadPartResult uploadResult = s3Client.uploadPart(uploadPartRequest);
            // Сохранение ETag в ваш список
            partETags.add(uploadResult.getPartETag());

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
            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(
                    bucketName,
                    keyName,
                    uploadId,
                    partETags);

            CompleteMultipartUploadResult compResult = s3Client.completeMultipartUpload(compRequest);
            logger.info("Загрузка завершена. Файл доступен по ключу: " + compResult.getKey());
            this.partETags.clear();
            return ResponseEntity.ok(new CompleteUploadResponse(compResult.getLocation()));
        } catch (AmazonServiceException e) {
            logger.error("Error occurred while completing upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CompleteUploadResponse(null));
        }
    }



}