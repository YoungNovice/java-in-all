package com.yangxuan.filter.simple;


import java.util.List;


public class DefaultInitChain implements InitChain {

    private List<InitFilter> initFilters;

    private int currentIndex = 0;

    public DefaultInitChain(List<InitFilter> initFilters) {
        this.initFilters = initFilters;
        // this.initFilters.sort(new OrderComparator());
    }

    @Override
    public void doChain(Object context) {
        initNext(this, context);
    }

    @Override
    public void initNext(InitChain chain, Object context) {
        if (currentIndex < initFilters.size()) {
            initFilters.get(currentIndex++).doFilter(chain, context);
        }
    }

}
