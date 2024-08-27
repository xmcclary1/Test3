package com.example.talent_api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

// import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

public interface BucketService {
    List<Bucket> getBucketList();
    boolean validateBucket(String bucketName);
    // void getObjectFromBucket(String bucketName, String objectName) throws IOException;
    void getObjectFromBucket(String objectName) throws IOException;
    // void putObjectIntoBucket(String bucketName, String objectName, MultipartFile filename);
    void putObjectIntoBucket(String usrName, MultipartFile filename);
    // void createBucket(String bucket);
    // void deleteObject(String bucketName, String key);
    void deleteObject(String key);
}
