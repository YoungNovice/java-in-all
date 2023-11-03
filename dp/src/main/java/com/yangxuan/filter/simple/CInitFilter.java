package com.yangxuan.filter.simple;

public class CInitFilter implements InitFilter {

    @Override
    public void doFilter(InitChain chain, Object context) {
        try {
            System.out.println("c filter");
            chain.initNext(chain, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}