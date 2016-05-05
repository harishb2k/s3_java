package com.s3;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class Main {
    private static String KEY;
    private static String SECRET;
    private static String BUCKET_NAME;

    public static void main(String args[]) {
        KEY = args[0];
        SECRET = args[1];
        BUCKET_NAME = args[2];
        System.out.println("Key=" + KEY + " Secret=" + SECRET + " Bucket=" + BUCKET_NAME);

        // Build a clinet object to be used for upload/download files
        BasicAWSCredentials b = new BasicAWSCredentials(KEY, SECRET);
        AmazonS3Client c = new AmazonS3Client(b);

        // Upload a file
        uploadFile(c);

        // Download file
        downloadFile(c);
    }

    private static void uploadFile(AmazonS3Client c) {
        // "/tmp/file_to_upload.txt" file will be uploaded to the S3 at "my_folder/my_file.txt" location
        File fileToUpload = new File("/tmp/file_to_upload.txt");
        PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, "my_folder/my_file.txt", fileToUpload);
        c.putObject(request);
        System.out.println("File is uploaded.");
    }

    private static void downloadFile(AmazonS3Client c) {
        // "my_folder/my_file.txt" file from S3 will be downloaded at "/tmp/file_to_upload_downloaded.txt" location
        GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, "my_folder/my_file.txt");
        File downloadedFile = new File("/tmp/file_to_upload_downloaded.txt");
        c.getObject(request, downloadedFile);
        System.out.println("File is downloaded.");
    }
}
