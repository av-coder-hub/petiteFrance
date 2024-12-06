package com.visitor.petitefrance.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dnjnesjre"); // Replace with your Cloudinary cloud name
        config.put("api_key", "231681824361646");       // Replace with your Cloudinary API key
        config.put("api_secret", "UMiWDF54jYduCx9Fugqtjqo-8Zc"); // Replace with your Cloudinary API secret

        this.cloudinary = new Cloudinary(config);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), new HashMap<>());
        return uploadResult.get("secure_url").toString(); // Extract and return the uploaded image URL
    }
}