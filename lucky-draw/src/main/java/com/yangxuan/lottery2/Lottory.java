package com.yangxuan.lottery2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * https://blog.csdn.net/u011129454/article/details/108142004?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-108142004-blog-106502179.pc_relevant_antiscanv2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-108142004-blog-106502179.pc_relevant_antiscanv2&utm_relevant_index=5
 * 扯淡
 */
public class Lottory {
    private final Random rm = new Random();

    public static class AwardConfig {
        ///probaility 为概率  0.2 即 20% 得概率  0.05  即5%的中奖概率
        private BigDecimal probaility;
        ///奖品名称
        private String name;

        public AwardConfig(BigDecimal probaility, String name) {
            this.probaility = probaility;
            this.name = name;
        }

        public BigDecimal getProbaility() {
            return probaility;
        }

        public void setProbaility(BigDecimal probaility) {
            this.probaility = probaility;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String reward(List<AwardConfig> giftList) {
        if (giftList == null || giftList.isEmpty()) {
            return null;
        }
        // 按照概率排序（如果不排序，下面随机数的区间需要做其他处理，否则无法己算正确的概率分布）
        Collections.sort(giftList, (o1, o2) -> {
            if (o1.getProbaility().doubleValue() > o2.getProbaility().doubleValue()) {
                return 1;
            } else if (o1.getProbaility().doubleValue() == o2.getProbaility().doubleValue()) {
                return 0;
            } else {
                return -1;
            }
        });

        BigDecimal[] probabilityList = new BigDecimal[giftList.size()];
        int index = 0;
        BigDecimal allRate = BigDecimal.valueOf(0);
        String targetNumber = "";
        for (AwardConfig gift : giftList) {
            probabilityList[index] = gift.getProbaility();
            allRate = allRate.add(gift.getProbaility());
            index++;
            if (gift.getProbaility().compareTo(BigDecimal.ONE) == 0) {
                /// 如果单个奖品概率等于1，直接中该奖品
                System.out.println("如个奖品概率等于1，直接中该奖品.");
                return gift.getName();
            }
            String proStr = gift.getProbaility().toString();
            String proStrTemp = proStr.substring(proStr.indexOf(".") + 1);
            if (proStrTemp.length() > targetNumber.length()) {
                /// 找到小数位最长的那个概率配置
                targetNumber = proStrTemp;
            }
        }
        if (allRate.intValue() != 1) {
            ///这里可以设置 所有奖品得概率总和必须为100%，当然也可以是其他数字，根据区间匹配。
            System.out.println("概率相加不等于1.");
            return null;
        }
        BigDecimal basicNumber = null;
        if (targetNumber != null && targetNumber.equals("") == false) {
            basicNumber = BigDecimal.valueOf(Math.pow(10, targetNumber.length()));
        }
        int diceRoll = rm.nextInt(basicNumber.intValue());
        if (diceRoll < 0) {
            System.err.println("随机数字小于0.");
            return null;
        }
        BigDecimal cumulative = BigDecimal.ZERO;
        for (int i = 0; i < giftList.size(); i++) {
            AwardConfig luckGift = giftList.get(i);
            if (luckGift != null && luckGift.getProbaility().compareTo(BigDecimal.ZERO) > 0) {
                cumulative = cumulative.add(luckGift.getProbaility().multiply(basicNumber));
                ///如果随机出得数字区间落在该奖品得区间内，即中得该奖品。这块得实现可以修改，只要数字区间为奖品区间就可以。
                if (diceRoll <= cumulative.intValue()) {
                    return giftList.get(i).getName();
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        List<AwardConfig> giftList = new ArrayList<>();
        giftList.add(new AwardConfig(new BigDecimal("0.2"), "奖品a"));
        giftList.add(new AwardConfig(new BigDecimal("0.8"), "奖品b"));
        new Lottory().reward(giftList);
    }

}

