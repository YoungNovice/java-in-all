package com.yangxuan.filter.simple;

public class BInitFilter implements InitFilter {

    @Override
    public void doFilter(InitChain chain, Object context) {
        try {
            System.out.println("b filter");
            chain.initNext(chain, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}