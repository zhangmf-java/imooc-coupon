package com.imooc.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 一个通用的抽象过滤器类
 * @author zmf
 * @date 2020/3/27 5:01 下午
 */
public abstract class AbstractZuulFilter extends ZuulFilter {

    /**
     * 用于在过滤器之间传递消息，数据保存在每个请求的threadlocal中
     * request和response都会存进来
     * 扩展了concurrentHashMap
     */
    RequestContext context;

    /**
     *     表示当前过滤器处理后，是否去下个过滤器的run方法
     */
    private final static String NEXT = "next";

    //true表示执行该过滤器的run方法，false则表示不执行
    @Override
    public boolean shouldFilter() {
        //获取当前线程中的数据
        RequestContext ctx = RequestContext.getCurrentContext();
        //getOrDefault() 返回指定键对应的值，如果没有该键，则返回默认值
        //zuul内置过滤器并不包含next这个key
        return (boolean) ctx.getOrDefault(NEXT, true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return cRun();
    }

    protected abstract Object cRun();

    Object fail(int code,String msg){
        context.set(NEXT,false);
        //ZuulResponse 设置false  不在继续执行后面的过滤器
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("test/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\":\"%s\"}",msg));
        return null;
    }

    Object success(){
        context.set(NEXT,true);
        return null;
    }
}
