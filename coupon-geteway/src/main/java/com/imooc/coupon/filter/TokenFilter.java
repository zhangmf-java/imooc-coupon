package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验请求中传递的token filter
 * @author zmf
 * @date 2020/3/27 10:13 下午
 */
@Component
@Slf4j
public class TokenFilter extends AbstractPreZuulFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request to %s",request.getMethod(),request.getRequestURL().toString()));
        Object token = request.getParameter("token");
        if (token == null){
            log.error("error:token is empty");
            return fail(401,"error:token is empty");
        }
        return success();
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
