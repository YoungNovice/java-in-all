//package com.yangxuan.lottery1;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LottoRsa {
//    private static final List<Prize> prizePool = new ArrayList<Prize>() {
//        {
//            this.add(new Prize("100元现金"));
//            this.add(new Prize("100元京东卡"));
//            this.add(new Prize("50元话费"));
//            this.add(new Prize("永久翻译包"));
//            this.add(new Prize("7天翻译包"));
//            this.add(new Prize("230颗蓝砖"));
//            this.add(new Prize("1个月会员"));
//            this.add(new Prize("100粉砖"));
//            this.add(new Prize("20粉砖"));
//            this.add(new Prize("5粉砖"));
//        }
//    };
//
//    public static Prize getPrize() {
//        int randomInt = RandomUtil.randomInt(100);
//        int randomFloat = RandomUtil.randomInt(10);
//
//        Float probability = Float.valueOf(randomInt + "." + randomFloat);
//
//        if (probability >= 0 && probability <= 0.1) {
//            return prizePool.get(0);
//        }
//        if (probability >= 0.1 && probability <= 0.5) {
//            return prizePool.get(1);
//        }
//        if (probability >= 0.5 && probability <= 1.5) {
//            return prizePool.get(2);
//        }
//        if (probability >= 1.5 && probability <= 4.5) {
//            return prizePool.get(3);
//        }
//        if (probability >= 4.5 && probability <= 9.5) {
//            return prizePool.get(4);
//        }
//        if (probability >= 9.5 && probability <= 14.5) {
//            return prizePool.get(5);
//        }
//        if (probability >= 14.5 && probability <= 22.5) {
//            return prizePool.get(6);
//        }
//        if (probability >= 22.5 && probability <= 32.5) {
//            return prizePool.get(7);
//        }
//        if (probability >= 32.5 && probability <= 52.5) {
//            return prizePool.get(8);
//        }
//        return prizePool.get(9);
//    }
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(getPrize());
//        }
//    }
//}