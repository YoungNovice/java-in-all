package com.yangxuan.csdn1;

public class TryWithResource11 {
    public TryWithResource11() {
    }

    public static void main(String[] args) {
        try {
            Connection conn = new Connection();
            Throwable var2 = null;

            try {
                conn.sendData();
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (conn != null) {
                    if (var2 != null) {
                        try {
                            conn.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        conn.close();
                    }
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }
}
