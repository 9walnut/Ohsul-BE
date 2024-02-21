package com.Ohsul.Ohsul.service;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private S3Template s3Template;

    public String uploadReviewImg(MultipartFile reviewImg) {
        try {
            String originalFileName = reviewImg.getOriginalFilename();
            S3Resource s3Resource = s3Template.upload(bucket, "reviewImg/" + originalFileName, reviewImg.getInputStream());
            return s3Resource.getURL().toString(); // 업로드된 url 반환
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deleteReviewImg(String fileName) {
        try {
            s3Template.deleteObject(bucket, "reviewImg/" + fileName);
            return "delete complete";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    public ResponseEntity<?> getReviewImg(String fileName) {
//        try {
//            S3Resource result = s3Template.download(bucket, "reviewImg/" + fileName);
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
