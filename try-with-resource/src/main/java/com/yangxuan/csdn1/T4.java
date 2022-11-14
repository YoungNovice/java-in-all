package com.yangxuan.csdn1;

public class T4 {

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test() throws Exception {
        Connection connection = null;
        Exception first = null;
        try {
            connection = new Connection();
            connection.sendData();
        } catch (Exception e) {
            first = e;
            throw e;
        } finally {
            if (connection != null) {
                if (first != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        first.addSuppressed(e);
                    }
                } else {
                    connection.close();
                }
            }
        }
    }
}
