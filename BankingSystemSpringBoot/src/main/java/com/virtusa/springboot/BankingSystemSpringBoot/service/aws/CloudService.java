package com.virtusa.springboot.BankingSystemSpringBoot.service.aws;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Response;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@Service
public class CloudService {

        private final S3Client s3Client;
        private final S3Presigner s3Presigner;
        private String bucketName;
        private long expirationMins;

        public CloudService(@Value("${cloud.aws.credentials.access-key}") String accessKey,
                        @Value("${cloud.aws.credentials.secret-key}") String secretKey,
                        @Value("${cloud.aws.region.static}") String region,
                        @Value("${cloud.aws.s3.bucket}") String bucketName,
                        @Value("${cloud.url.expiration-in-minutes}") long expirationMins) {

                this.bucketName = bucketName;
                this.expirationMins = expirationMins;
                AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

                this.s3Client = S3Client.builder()
                                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                                .region(Region.of(region))
                                .build();

                this.s3Presigner = S3Presigner.builder()
                                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                                .region(Region.of(region))
                                .build();
        }

        public S3Response uploadFile(File file) {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(file.getName())
                                .build();

                return s3Client.putObject(putObjectRequest, Paths.get(file.getPath()));
        }

        // // Method to generate a pre-signed URL for uploading a file
        // public URL generatePresignedUrlForUpload(String fileName) {
        // PresignedPutObjectRequest presignedRequest =
        // s3Presigner.presignPutObject(builder -> builder
        // .putObjectRequest(PutObjectRequest.builder()
        // .bucket(bucketName)
        // .key(fileName)
        // .build())
        // .signatureDuration(Duration.ofMinutes(expirationMins)) // URL valid for 10
        // minutes
        // );
        // return presignedRequest.url();
        // }

        // Method to generate a pre-signed URL for downloading a file
        public URL generatePresignedUrlForDownload(String fileName) {
                PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(builder -> builder
                                .getObjectRequest(GetObjectRequest.builder()
                                                .bucket(bucketName)
                                                .key(fileName)
                                                .build())
                                .signatureDuration(Duration.ofMinutes(expirationMins)) // URL valid for 10 minutes
                );

                return presignedRequest.url();
        }
}
