package com.yangxuan.走台阶;

/**
 * 有n步台阶 一次只能上一步或者两步 共有多少中走法
 *
 * n = 1   一步                              f(1) = 1
 * n = 2   1.两个一步 或者 2.走两步上去         f(2) = 2
 * n = 3   先到达f(1) 然后跨两步上去
 *         先到达f(2) 然后跨一步上去            f(3) = f(2) + f(1)
 *
 * n = x   先到达f(x-2) 然后跨两步上去
 *         先到达f(x-1) 然后跨一步上去            f(x) = f(x-1) + f(x-2)
 */
public class GoStep {

    public static void main(String[] args) {
        double d = 25/2;
        System.out.println(d);
        /*long start = System.currentTimeMillis();
        System.out.println(f(45));
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));

        long start1 = System.currentTimeMillis();
        System.out.println(loop(45));
        long end1 = System.currentTimeMillis();
        System.out.println("time1: " + (end1 - start1));*/
    }

    /**
     * 递归
     */
    public static int f(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n-1) + f(n-2);
    }

    /**
     * 迭代
     */
    public static int loop(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int fn = 0;
        int fn_1 = 2;
        int fn_2 = 1;
        for (int i = 0; i < n-2; i++) {
            fn = fn_1 + fn_2;
            fn_2 = fn_1;
            fn_1 = fn;
        }
        return fn;
    }
}
