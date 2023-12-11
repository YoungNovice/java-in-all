package com.lalami.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Override
    @PostConstruct
    public void transferToPdf() {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/xml.pdf");
        File fos = new File("/Users/yangxuan/codespace/github/java-in-all/bi-auto/temp/xml1.pdf");
        try {
            copyFileUsingStream(is, fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transferToImg() {

    }

    private static void copyFileUsingStream(InputStream is, File dest) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
