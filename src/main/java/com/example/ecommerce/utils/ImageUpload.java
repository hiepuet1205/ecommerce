package com.example.ecommerce.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.ecommerce.config.AmazonConfig;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Component
public class ImageUpload {
    @Autowired
    private AmazonConfig amazonConfig;

    @Value("${AMAZON_BUCKET_NAME}")
    private String bucketName;

//    private String bucketName = "hiepuetnbk";

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"jpe", "png", "bmp", "jpeg"}).contains(fileExtension);
    }

    // BUG
//    private ByteArrayOutputStream resizeImage(MultipartFile file, int targetSize) throws IOException {
//        BufferedImage image = ImageIO.read(file.getInputStream());
//        BufferedImage resizedImage = Scalr.resize(image, targetSize);
//
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        ImageIO.write(resizedImage, "jpg", os);
//
//        return os;
//    }

    public String saveImageToS3(MultipartFile file) throws IOException {
        AmazonS3 s3client = amazonConfig.s3();

        if (!isImageFile(file)) {
            throw new RuntimeException("File is not image!");
        }

        String fileName = UUID.randomUUID().toString() + ".jpg";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), objectMetadata));

        String url = s3client.getUrl(bucketName, fileName).toString();

        return url;
    }

}
