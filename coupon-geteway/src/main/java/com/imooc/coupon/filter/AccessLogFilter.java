package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zmf
 * @date 2020/3/27 10:50 下午
 */
@Slf4j
@Component
public class AccessLogFilter extends AbstractPostZuulFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        //获取在prefilter 设置的请求开始时间戳
        Long startTime = (Long) context.get("startTime");
        String requestURI = request.getRequestURI();
        long duration = System.currentTimeMillis() - startTime;

        //从网关通过的请求，都会打印日志记录：uri + duration
        log.info("uri:{},duration:{}",requestURI,duration);
        return success();
    }

    @Override
    public int filterOrder() {
        //post filter 默认order为1000 ，order大于1000 的filter 不会被执行
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }
}
