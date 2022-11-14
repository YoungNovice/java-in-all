package com.yangxuan.csdn1;

public class Connection implements AutoCloseable {

    public void sendData() {
        throw new RuntimeException("send data");
    }

    @Override
    public void close() throws Exception {
        throw new RuntimeException("close");
    }

}
