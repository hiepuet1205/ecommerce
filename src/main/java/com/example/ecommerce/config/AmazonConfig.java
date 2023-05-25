package com.example.ecommerce.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Value("${AMAZON_ACCESS_KEY}")
    private String accessKey;

    @Value("${AMAZON_SECRET_KEY}")
    private String secretKey;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
//        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAU3ZOS6RFQ2M3JL6H", "C7N67HZGmfCizUmeiP8Gz21yywTdq0NRNMoj6rN7");

        return AmazonS3ClientBuilder
                .standard()
                .withRegion("ap-southeast-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}
