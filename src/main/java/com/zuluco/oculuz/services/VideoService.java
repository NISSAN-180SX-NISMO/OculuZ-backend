package com.zuluco.oculuz.services;

import com.zuluco.oculuz.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    private AmazonS3 s3Client;

    private static final String bucketName = "fd22a2a2-oculuz-media-storage";
    private static String keyName;

    List<PartETag> partETags = new ArrayList<>();

    private void clearStatement() {
        partETags.clear();
        keyName = "video";
    }

    public InitiateMultipartUploadResult initUpload(String fileName) {
        clearStatement();
        keyName += "/" + fileName;
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, keyName);
        return s3Client.initiateMultipartUpload(initiateMultipartUploadRequest);
    }

    public UploadPartResult handleFileUpload(MultipartFile file, int partNumber, String uploadId) throws IOException {
        InputStream inputStream = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());

        UploadPartRequest uploadPartRequest = new UploadPartRequest()
                .withBucketName(bucketName)
                .withKey(keyName)
                .withUploadId(uploadId)
                .withPartNumber(partNumber)
                .withInputStream(inputStream)
                .withPartSize(file.getSize());

        UploadPartResult uploadResult = s3Client.uploadPart(uploadPartRequest);
        partETags.add(uploadResult.getPartETag());

        return uploadResult;
    }

    public CompleteMultipartUploadResult completeMultipartUpload(String uploadId) {
        CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(
                bucketName,
                keyName,
                uploadId,
                partETags);

        CompleteMultipartUploadResult compResult = s3Client.completeMultipartUpload(compRequest);
        clearStatement();
        return compResult;
    }
}
