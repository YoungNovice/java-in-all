package com.yangxuan.filter.mashibing;

public class Main {

    /*
     * filter-chain本身也是一个filter
     * 它是由多个功能组成的filter 优雅得一批
     *
     **/
    public static void main(String[] args) {
        String msg = "<html>testMsg</html>";
        Request request = new Request();
        request.requestStr = msg;
        Response response = new Response();
        response.responseStr = "responseStr";

        FilterChain fc = new FilterChain();
        fc.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());
        fc.doFilter(request, response, fc);
    }

}