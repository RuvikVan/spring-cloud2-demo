package com.rv.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessTokenFilter extends ZuulFilter {

    // token
    public final static String ACCESS_TOKEN = "access_token";

    // token lifecycle
    public final static String TOKEN_EXPIRES_IN = "token_expires_in";

    // token
    public final static String TOKEN_TIME = "token_time";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();




        try {

            log.info(String.format("%s AccessTokenFilter request to %s", request.getMethod(), request.getRequestURL().toString()));

            String uri = request.getRequestURL().toString();
            if(uri.contains("/cauds-account/") || uri.contains("/uaa")) {
                ctx.setSendZuulResponse(true);// 对该请求进行路由
                ctx.setResponseStatusCode(200);
                ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
                return null;
            }

            //String startTime = request.getParameter(TOKEN_TIME);
            //String expires_in = request.getParameter(TOKEN_EXPIRES_IN);

            String startTime = request.getHeader("TOKEN_TIME");
            String expires_in = request.getHeader(TOKEN_EXPIRES_IN);

            log.info(TOKEN_TIME + ":" + startTime + "," + TOKEN_EXPIRES_IN + ":" + expires_in);
            Long start = Long.parseLong(startTime);
            Long expires = Long.parseLong(expires_in);
            long now = System.currentTimeMillis();

            if((now - start) < expires) {// 如果请求的参数不为空，且值为chhliu时，则通过
                ctx.setSendZuulResponse(true);// 对该请求进行路由
                ctx.setResponseStatusCode(200);
                ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
                return null;
            }else{
                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                ctx.setResponseStatusCode(401);// 返回错误码
                ctx.setResponseBody("{\"result\":\"token is expired!\"}");// 返回错误内容
                ctx.set("isSuccess", false);
                return null;
            }

        } catch (Exception e) {
            log.error("AccessTokenFilter run error.",e);
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"Parameter may error!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;

        }


    }
}
