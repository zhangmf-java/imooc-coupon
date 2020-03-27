package com.imooc.coupon.advice;

import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author zmf
 * @date 2020/3/28 12:07 上午
 */
@RestControllerAdvice
public class GlobleExceptionAdvice {
    /**
     * 对CouponException 统一处理
     * anno @ExceptionHandler 异常处理器,对指定的异常进行处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request, CouponException ex){
        CommonResponse<String> response = new CommonResponse<String>(-1,"business error");
        response.setData(ex.getMessage());
        return response;
    }
}
