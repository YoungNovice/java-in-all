package com.yangxuan.filter.simple;


public interface InitChain {

    void doChain(Object context);

    void initNext(InitChain chain, Object context);
}
