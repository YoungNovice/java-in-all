package com.yangxuan.filter.simple;

public interface InitFilter {

    void doFilter(InitChain chain, Object context);

}
