package com.imooc.coupon.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流过滤器
 * @author zmf
 * @date 2020/3/27 10:27 下午
 */
@Slf4j
@Component
//@SuppressWarnings("all")
public class RateLimiterFilter extends AbstractPreZuulFilter {

    /**
     * 限流器
     *  2.0 ： 每秒可以获得两个令牌
     */
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        if (rateLimiter.tryAcquire()){
            log.info("get rate token success");
            return success();
        }else{
            log.error("rate limte:{}",request.getRequestURI());
            return fail(402,"error:rate limite");
        }
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
