package com.example.talent_api.controller;

import java.util.HashMap;
// import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.Bucket;
// import com.amazonaws.services.s3.model.PresignedUrlDownloadRequest;
import com.example.talent_api.service.BucketService;

@RestController
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    BucketService bucketService;

    @GetMapping
    public void getBucketList() {
        List<Bucket> bucketList = bucketService.getBucketList();
        System.out.println("bucketList:"+bucketList);
    }

    @GetMapping("/downloadObj")
    public ResponseEntity<?> downloadObject(@RequestParam("objName") String objName) throws Exception {
        try {
            bucketService.getObjectFromBucket(objName);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Object successfully added to Bucket");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        
    }

    // @PostMapping("/uploadObj")
    // public void uploadObject(@RequestParam("bucketName") String bucketName, @RequestParam("usrName") String usrName, @RequestParam("file") MultipartFile file) throws Exception {
    //     bucketService.putObjectIntoBucket(bucketName, usrName, file);
    // }

    @PostMapping("/uploadObj")
    public ResponseEntity<?> uploadObject(@RequestParam("usrName") String usrName, @RequestParam("file") MultipartFile file) throws Exception {
        try {
            bucketService.putObjectIntoBucket(usrName, file);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Object successfully added to Bucket");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        
    }

    // @PostMapping("/createBucket")
    // public String createBucket(@RequestParam("bucketName") String bucketName) {
    //     bucketService.createBucket(bucketName);
    //     return "Bucket created";
    // }

    @DeleteMapping("/deleteObj")
    // public String deleteObj(@RequestParam String bucket, @RequestParam("filename") String filename) {
    public ResponseEntity<?> deleteObj(@RequestParam("filename") String filename) {
        try {
            bucketService.deleteObject(filename);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Object succesfully deleted from Bucket");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
