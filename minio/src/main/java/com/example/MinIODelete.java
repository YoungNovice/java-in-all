package com.example;

import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinIODelete {

    static MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials("minioadmin", "minioadmin").build();

    //删除文件
    public static void delete(String bucket, String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(filepath).build());
            System.out.println("删除成功");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        delete("testbucket", "tcp.jpg");
        delete("testbucket", "doc/jihua.doc");
    }

}
