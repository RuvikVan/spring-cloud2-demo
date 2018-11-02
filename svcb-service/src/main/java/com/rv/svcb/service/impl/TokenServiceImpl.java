package com.rv.svcb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rv.svcb.service.TokenService;
import com.rv.svcb.util.SpiderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    private String tokenUrl = "http://192.168.1.200:8060/uaa/oauth/token";

    /**
     * base64加密的 "client:secret"
     */
    private String authorization = "Basic Y2xpZW50OnNlY3JldA==";


    @Override
    public String getToken() {

        try {
            long token_time = System.currentTimeMillis();
            long token_expires_in = 36000;

            String url = tokenUrl;

            String param = "password=password&username=anil&grant_type=password&scope=read%20write&";

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Connection", "keep-alive");
            headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Mobile Safari/537.36");
            headers.put("Authorization", authorization);

            headers.put("token_time", token_time + "");
            headers.put("token_expires_in", token_expires_in + "");

            String rs = SpiderUtil.sendPost(url, param, headers);

            JSONObject obj = JSON.parseObject(rs);
            String access_token = obj.getString("access_token");
            access_token = "Bearer " + access_token;

            return access_token;
        } catch (Exception e) {
            LOG.error("Token获取失败，" + e);
            return null;
        }
    }
}
