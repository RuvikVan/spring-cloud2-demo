package com.rv.svcb.client;

import com.rv.svcb.config.FeignHeaderInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "svca-service", fallback = ServiceAClient.ServiceAClientFallback.class, configuration = FeignHeaderInterceptor.class)
public interface ServiceAClient {

    @GetMapping(value = "/qwe")
    String printServiceB();

    @Component
    class ServiceAClientFallback implements ServiceAClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAClientFallback.class);

        @Override
        public String printServiceB() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE B FAILED! - FALLING BACK";
        }
    }
}