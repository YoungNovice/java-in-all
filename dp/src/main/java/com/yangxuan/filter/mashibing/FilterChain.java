package com.yangxuan.filter.mashibing;
 
import java.util.ArrayList;
import java.util.List;
 
public class FilterChain implements Filter {
 
	private final List<Filter> filters = new ArrayList<>();
	int index = 0;
	
	public FilterChain addFilter(Filter filter) {
		filters.add(filter);
		return this;
	}
	
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		
		if(index == filters.size()) {
			return;
		}
		Filter filter = filters.get(index);
		index++;
		filter.doFilter(request, response, chain);
	}
 
}