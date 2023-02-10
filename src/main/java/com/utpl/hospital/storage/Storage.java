package com.utpl.hospital.storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.utpl.hospital.buckets.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class Storage {
    private final AmazonS3 amazonS3;
    @Autowired
    public Storage(AmazonS3 amazonS3){
        this.amazonS3=amazonS3;
    }

    public void save(String path,
                     String fileName,
                     Optional<ObjectMetadata> optionalMetaData,
                     InputStream inputStream){
        ObjectMetadata metaData=optionalMetaData.get();
        try{
            amazonS3.putObject(path,fileName,inputStream,metaData);
        }catch (AmazonServiceException e){
            throw  new IllegalStateException("failed to store the file", e);
        }
    }
    public byte[] downloadByteArray(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
    public byte[] downloadBase64(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            byte[] array= IOUtils.toByteArray(object.getObjectContent());
            return Base64Utils.encode(array);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
    public String getLink(String path, String key, Bucket bucket) {
        try {
            String resourceUrl="https://"+bucket.getName()+".s3.amazonaws.com/"+path+"/"+key;
            return resourceUrl;
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
}
