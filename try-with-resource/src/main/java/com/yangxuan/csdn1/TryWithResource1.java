package com.yangxuan.csdn1;

public class TryWithResource1 {

    public static void main(String[] args) {
        try (Connection conn = new Connection()) {
            conn.sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
