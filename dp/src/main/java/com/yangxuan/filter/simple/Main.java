package com.yangxuan.filter.simple;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * chain持有所有的filter
     * chain触发第一个filter执行 并传入chain自己 通过它在filter中执行下一个
     * chain中需要有一个index维持执行到哪一个了
     **/
    public static void main(String[] args) {
        List<InitFilter> filters = new ArrayList<>();
        filters.add(new AInitFilter());
        filters.add(new BInitFilter());
        filters.add(new CInitFilter());
        DefaultInitChain chain = new DefaultInitChain(filters);
        chain.doChain(new Object());
    }
}
