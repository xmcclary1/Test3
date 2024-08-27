package com.example.talent_api.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.talent_api.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    Logger LOG = LogManager.getLogger(BucketServiceImpl.class);

    @Autowired
    AmazonS3 s3Client;

    @Value("${AWS_BUCKET}")
    String awsBucket;

    @Override
    public List<Bucket> getBucketList() {
        LOG.info("Getting bucket list... ");
        return s3Client.listBuckets();
    }

    @Override
    public boolean validateBucket(String bucketName) {
        List<Bucket> bucketList = getBucketList();
        LOG.info("Bucket list:"+bucketList);
        return bucketList.stream().anyMatch(m -> bucketName.equals(m.getName()));
    }

    @Override
    public void getObjectFromBucket(String objectName) throws IOException {
        S3Object s3Object = s3Client.getObject(awsBucket, objectName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        // Get the user's Downloads directory
        String home = System.getProperty("user.home");
        String downloadsDir = home + File.separator + "Downloads";
        Path outputPath = Paths.get(downloadsDir, objectName);

        // Copy the input stream to the file
        Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);

        inputStream.close();
    }

    // @Override 
    // public void createBucket(String bucketName) {
    //     s3Client.createBucket(bucketName);
    // }

    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

    @Override
    // public void putObjectIntoBucket(String bucketName, String username, MultipartFile filename) {
    public void putObjectIntoBucket(String username, MultipartFile filename) {
            System.out.println("Bucket Name: " + awsBucket);
        try {
            File file = convertMultipartFileToFile(filename);
            String key = username + "/" + filename.getOriginalFilename();
            s3Client.putObject(awsBucket, key, file);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.out.println("Could not put object in bucket");
            // System.exit(1);

        }
    }

    @Override
    public void deleteObject(String filename) {
        s3Client.deleteObject(awsBucket, filename);
    }
}
