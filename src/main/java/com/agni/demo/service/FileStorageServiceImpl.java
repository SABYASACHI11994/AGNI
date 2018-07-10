package com.agni.demo.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
@Service
public class FileStorageServiceImpl implements FileStorageService
{


//    private final Path fileStorageLocation;
    
	public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = Paths.get("C:\\Users\\sa356897\\Desktop\\@AMRIT-TechClarityOnPendingItems.pptx").normalize();//this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }

}

