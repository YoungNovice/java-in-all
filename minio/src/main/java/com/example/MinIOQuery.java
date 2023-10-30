package com.example;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinIOQuery {

    static MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials("minioadmin", "minioadmin").build();

    //下载文件outFile就是下载到本地的路径
    public static void getFile(String bucket, String filepath, String outFile) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            try (InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(filepath)
                            .build());
                 FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            ) {
                // Read data from stream
                IOUtils.copy(stream, fileOutputStream);
                System.out.println("下载成功");
            }

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        getFile("testbucket", "doc/jihua.doc", "jihua.doc");
    }

}
