package com.example;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinIOUpload {

    static MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials("minioadmin", "minioadmin").build();

    //上传文件
    public static void upload() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("testbucket").build());
            //检查testbucket桶是否创建，没有创建自动创建
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("testbucket").build());
            } else {
                System.out.println("Bucket 'testbucket' already exists.");
            }
            //上传tcp.jpg
            minioClient.uploadObject(UploadObjectArgs.builder().bucket("testbucket").object("tcp.jpg").filename("/Users/yangxuan/Downloads/tcp.jpg").build());
            //上传jihua.doc,上传到doc子目录
            minioClient.uploadObject(UploadObjectArgs.builder().bucket("testbucket").object("doc/jihua.doc").filename("/Users/yangxuan/Downloads/jihua.doc").build());
            System.out.println("上传成功");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        upload();
    }


}
