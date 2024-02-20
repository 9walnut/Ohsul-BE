package com.Ohsul.Ohsul.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadReviewImg(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String fileUrl = "https://" + bucket + ".s3.amazonaws.com/reviewImg/" + fileName;
            ObjectMetadata objectMetadata = new ObjectMetadata();

            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3Client.putObject(new PutObjectRequest(bucket, "reviewImg/" + fileName, file.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return fileUrl;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Boolean deleteReviewImg(String fileName) throws IOException {
        try {
            amazonS3Client.deleteObject(bucket, "reviewImg/" + fileName);
        } catch (Exception e) {
            throw new IOException("이미지 삭제 오류", e);
        }
        return true;
    }
}
