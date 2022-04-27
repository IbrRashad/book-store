package com.rashad.bookstore.service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploader {
    public static byte[] upload(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")){
                throw new Exception("Filename contains invalid path sequence"+fileName);
            }

            byte[] data = file.getBytes();
            return data;
        }
        catch (Exception e){
            throw new Exception("Could not save File: "+fileName);
        }
    }
}
