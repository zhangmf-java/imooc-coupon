package com.imooc.coupon.advice;

import com.imooc.coupon.annotation.IgnoreResponseAdvice;
import com.imooc.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一响应
 * @author zmf
 * @date 2020/3/27 11:42 下午
 */
@RestControllerAdvice
//ResponseBodyAdvice是对Response作处理
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否需要对响应进行处理
     * @param methodParameter controller的定义
     * @param aClass 继承自http消息转换器
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        //如果当前方法所在的类标识了@IgnoreResponseAdvice 则不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        //如果当前方法标识了@IgnoreResponseAdvice 则不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        //对响应进行处理，执行beforeBodyWrite（）
        return true;
    }

    /**
     * 写入响应流之前的处理
     * @param o controller 的返回对象
     * @param methodParameter controller 的声明方法
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //定义最终的返回对象
        CommonResponse<Object> commonResponse = new CommonResponse<Object>(0,"");
        //如果 o 是null，response不需要设置data
        if (null == o){
            return commonResponse;
            //如果o已经是CommonResponse,不需要再次处理
        }else if (o instanceof CommonResponse){
            commonResponse = (CommonResponse<Object>) o;
            //否则把响应对象作为CommonResponse的data部分
        }else{
            commonResponse.setData(o);
        }
        return commonResponse;
    }
}
