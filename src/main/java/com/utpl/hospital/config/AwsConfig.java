package com.utpl.hospital.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Bean
    public AmazonS3 amazonS3(){
        AWSCredentials awsCredentials=new BasicAWSCredentials(
                "ACCESS KEY",
                "SECRET KEY"
        );
        Region apSouth1 = Region.getRegion(Regions.US_EAST_1);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(String.valueOf(apSouth1))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
