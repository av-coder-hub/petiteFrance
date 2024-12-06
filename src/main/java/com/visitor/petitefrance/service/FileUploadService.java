package com.visitor.petitefrance.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;  // Correct import for UUID

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service // Marks the class as a Spring service component
public class FileUploadService {
    private String uploadDir = "D:\\Project PY\\petitefrance\\uploads";

    public void handleFileUpload(MultipartFile file) throws IOException {
        // Get the original file name
        String originalFileName = file.getOriginalFilename();
        
        // Generate a unique filename using UUID
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Create the path where the file will be saved
        String filePath = uploadDir + File.separator + uniqueFileName;

        // Transfer the file to the specified location
        File destination = new File(filePath);
        file.transferTo(destination);

        System.out.println("File uploaded successfully to: " + filePath);
    }
    

    // Save the file to the system and return its file path
    public String saveFile(MultipartFile file, String uploadDir) throws IOException {
        // Ensure the directory exists, create if not
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create upload directory: " + uploadDir);
            }
        }

        // Define the file path
        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        File destinationFile = new File(filePath);

        // Validate directory before file transfer
        if (!destinationFile.getParentFile().exists()) {
            throw new IOException("Parent directory does not exist: " + destinationFile.getParent());
        }

        if (!destinationFile.getParentFile().canWrite()) {
            throw new IOException("Cannot write to directory: " + destinationFile.getParent());
        }

        // Attempt to save the file
        try {
            System.out.println("Saving file to: " + filePath); // Log the file path
            file.transferTo(destinationFile);
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.err.println("Error while saving file: " + e.getMessage());
            throw e; // Rethrow the exception for further debugging
        }

        return filePath;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
