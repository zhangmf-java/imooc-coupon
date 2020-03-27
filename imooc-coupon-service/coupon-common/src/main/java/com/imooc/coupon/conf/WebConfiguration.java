package com.imooc.coupon.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 定制http消息转换器
 * 将java的对象转换为http的输出流
 * springboot底层通过HttpMessageConverter，依靠Jackson库将java对象输出为json格式
 * 当有多个转换器可用时，会根据消息对象的类型和内容的类型选择合适的转换器
 * MappingJackson2HttpMessageConverter 可以将对象转换为json格式
 * 使未来的接口都以json的格式返回，不用springboot帮忙挑选消息转换器
 * @author zmf
 * @date 2020/3/27 11:19 下午
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
