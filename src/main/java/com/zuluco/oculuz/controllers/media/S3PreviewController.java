package com.zuluco.oculuz.controllers.media;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.zuluco.oculuz.payload.response.CompletePreviewUploadResponse;
import com.zuluco.oculuz.payload.response.MessageResponse;
import com.zuluco.oculuz.services.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/preview")
public class S3PreviewController {
    @Autowired
    private S3Service s3Service;

    private static final Logger logger = LoggerFactory.getLogger(S3VideoController.class);

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3Service.uploadFile(file);
            logger.info("File {} uploaded successfully", file.getOriginalFilename());
            return ResponseEntity.ok(new CompletePreviewUploadResponse(fileUrl));
        } catch (Exception e) {
            logger.error("Error occurred while uploading file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error occurred while uploading file"));
        }
    }
}