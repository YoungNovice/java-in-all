package com.yangxuan.hello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @HystrixCommand(fallbackMethod = "helloFallback")
    /*@HystrixCommand(
            // Command 配置,设置操作时间为 100 毫秒
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            // 设置 fallback 方法
            fallbackMethod = "fallbackForGetUsers"
    )*/

   /*@HystrixCommand(
        fallbackMethod = "getOrderPageListFallback",
        threadPoolProperties = {  //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
                @HystrixProperty(name = "coreSize", value = "10"),
                @HystrixProperty(name = "maxQueueSize", value = "100"),
                @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20")},
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"), //命令执行超时时间
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //若干10s一个窗口内失败三次, 则达到触发熔断的最少请求量
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000") //断路30s后尝试执行, 默认为5s
        })*/
    public String helloService(){
        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }
        String a = "helloword";
        return a;
    }

    public Integer queryByOrderId() {
        throw new RuntimeException("错误");
//        return 100;
    }

    public String helloFallback(){
        return "error";
    }

}
