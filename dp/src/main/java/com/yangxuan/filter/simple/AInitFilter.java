package com.yangxuan.filter.simple;

public class AInitFilter implements InitFilter {

    @Override
    public void doFilter(InitChain chain, Object context) {
        try {
            System.out.println("a filter");
            chain.initNext(chain, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}