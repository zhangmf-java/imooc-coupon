package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 在过滤器中存储客户端发起请求的时间戳
 * @author zmf
 * @date 2020/3/27 10:47 下午
 */
@Slf4j
@Component
public class PreRequestFilter extends AbstractPreZuulFilter {
    @Override
    protected Object cRun() {
        context.set("startTime",System.currentTimeMillis());
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
