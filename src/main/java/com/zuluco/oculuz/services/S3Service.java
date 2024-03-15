package com.zuluco.oculuz.services;

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
public class S3Service {
    @Autowired
    private AmazonS3 s3Client;

    private static final String bucketName = "fd22a2a2-oculuz-media-storage";
    private static String videoKeyName;

    List<PartETag> partETags = new ArrayList<>();

    private void clearStatement() {
        partETags.clear();
        videoKeyName = "video";
    }

    public InitiateMultipartUploadResult initUpload(String fileName) {
        clearStatement();
        videoKeyName += "/" + fileName;
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, videoKeyName);
        return s3Client.initiateMultipartUpload(initiateMultipartUploadRequest);
    }

    public UploadPartResult handleFileUpload(MultipartFile file, int partNumber, String uploadId) throws IOException {
        InputStream inputStream = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());

        UploadPartRequest uploadPartRequest = new UploadPartRequest()
                .withBucketName(bucketName)
                .withKey(videoKeyName)
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
                videoKeyName,
                uploadId,
                partETags);

        CompleteMultipartUploadResult compResult = s3Client.completeMultipartUpload(compRequest);
        clearStatement();
        return compResult;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "preview/" + fileName, file.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead); // public for all

            s3Client.putObject(putObjectRequest);

            return s3Client.getUrl(bucketName, "preview/" + fileName).toString();

        } catch (IOException e) {
            throw new RuntimeException("Issue occurred while trying to upload the file.", e);
        }
    }

}
