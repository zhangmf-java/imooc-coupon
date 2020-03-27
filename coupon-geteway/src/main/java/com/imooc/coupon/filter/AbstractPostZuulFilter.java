package com.imooc.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author zmf
 * @date 2020/3/27 5:36 下午
 */
public abstract class AbstractPostZuulFilter extends AbstractZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
}
