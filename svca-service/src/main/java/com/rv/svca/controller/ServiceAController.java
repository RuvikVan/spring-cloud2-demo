package com.rv.svca.controller;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    @GetMapping(path = "/qwe")
    public String getCurrentAccount() {
        return "qwe";
    }

//    @Value("${name:unknown}")
//    private String name;
//
//    @Autowired
//    EurekaDiscoveryClient discoveryClient;
//    @Autowired
//    private ServiceBClient serviceBClient;
//
//    @GetMapping(value = "/")
//    public String printServiceA() {
//        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
//        return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>name:" + name + "<br/>" + serviceBClient.printServiceB();
//    }
//
//    @GetMapping(path = "/current")
//    public Principal getCurrentAccount(Principal principal) {
//        return principal;
//    }
}