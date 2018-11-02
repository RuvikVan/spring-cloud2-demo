package com.rv.svca.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ServiceAController {

    @GetMapping(path = "/qwe")
    public String getCurrentAccount() {
        return "qwe";
    }

}