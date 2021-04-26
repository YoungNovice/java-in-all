package com.yangxuan.hello;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HystrixObservableCommand
 *
 * @author yangxuan
 */
public class QueryOrderIdCommand extends HystrixCommand<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(QueryOrderIdCommand.class);

    private HelloService helloService;

    public QueryOrderIdCommand(HelloService helloService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("queryByOrderId"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withCircuitBreakerRequestVolumeThreshold(10)//至少有10个请求，熔断器才进行错误率的计算
                    .withCircuitBreakerSleepWindowInMilliseconds(5000)//熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                    .withCircuitBreakerErrorThresholdPercentage(50)//错误率达到50开启熔断保护
                    //.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                    //.withExecutionIsolationSemaphoreMaxConcurrentRequests(10)
                    .withExecutionTimeoutEnabled(true))
               .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)));
        this.helloService = helloService;
    }



    @Override
    protected Integer run() throws Exception {
        return helloService.queryByOrderId();
    }

    @Override
    protected Integer getFallback() {
        return -1;
    }

}
