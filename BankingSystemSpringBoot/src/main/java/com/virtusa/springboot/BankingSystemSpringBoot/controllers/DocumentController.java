package com.virtusa.springboot.BankingSystemSpringBoot.controllers;

import java.io.File;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.virtusa.springboot.BankingSystemSpringBoot.helper.FileResolver;
import com.virtusa.springboot.BankingSystemSpringBoot.service.aws.CloudService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/document")
@AllArgsConstructor
public class DocumentController {

    private final CloudService cloudService;
    private final FileResolver fileResolver;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        File convertedFile = fileResolver.convertMultiPartToFile(file);
        cloudService.uploadFile(convertedFile);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/download-url")
    public ResponseEntity<String> getDownloadUrl(@RequestParam String fileName) {
        URL url = cloudService.generatePresignedUrlForDownload(fileName);
        return ResponseEntity.ok(url.toString());
    }
    
}
