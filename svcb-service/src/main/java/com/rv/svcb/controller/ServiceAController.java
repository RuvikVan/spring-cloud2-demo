package com.rv.svcb.controller;

import com.rv.svcb.client.ServiceAClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ServiceAController {

    @Autowired
    private ServiceAClient serviceAClient;

    @GetMapping(path = "/qwe")
    public String getCurrentAccount() {
        return serviceAClient.printServiceB();
    }

}