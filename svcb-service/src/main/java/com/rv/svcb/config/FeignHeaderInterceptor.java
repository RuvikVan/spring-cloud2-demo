package com.rv.svcb.config;

import com.rv.svcb.service.impl.TokenServiceImpl;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class FeignHeaderInterceptor implements RequestInterceptor {

    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    public void apply(RequestTemplate template) {
        template.header(HttpHeaders.AUTHORIZATION, tokenService.getToken());
    }
}

