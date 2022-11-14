package com.yangxuan.csdn1;

public class ReturnLine {

    public static void main(String[] args) {
        // 规则1: 如果finally里面有return 则是finally里面的 (try catch里面的都得靠后站)
        // 规则2: 其他的是正常思维 不需要再解释了
        System.out.println(test());
    }

    public static String test() {
        Object o = null;
        try {
            o.toString();
            return "try";
        } catch (Exception e) {
            o.toString();
            return "catch";
        } finally {
        }
    }
}
