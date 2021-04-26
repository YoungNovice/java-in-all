package com.yangxuan.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    HelloService helloService;

    @RequestMapping(value = "demo", method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.helloService();
    }

    @RequestMapping(value = "byId", method = RequestMethod.GET)
    public Integer queryByOrderIdCommand() {
        QueryOrderIdCommand command = new QueryOrderIdCommand(helloService);
        Integer result = command.execute();
        String method = result == -1 ? "fallback" : "run";
        logger.info("result:{},method:{},isCircuitBreakerOpen:{}", result, method, command.isCircuitBreakerOpen());
        return result;
    }

}
