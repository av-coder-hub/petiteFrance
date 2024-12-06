package com.visitor.petitefrance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.petitefrance.service.FileUploadService;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService; // Injecting the service

    // Endpoint to handle file uploads
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // The upload directory
            String uploadDir = fileUploadService.getUploadDir(); // Use the directory set in the service
            
            // Save the file using the service
            String filePath = fileUploadService.saveFile(file, uploadDir);
            
            // Return success response
            return "File uploaded successfully to: " + filePath;
        } catch (IOException e) {
            // Return failure response with error details
            return "File upload failed: " + e.getMessage();
        }
    }
}
