package com.cs.personer.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * fastJson配置
 *
 * @author Jack
 */
@Configuration
public class HttpConverterConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        fastJsonConfig.setSerializeFilters(dateFormat());
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    private ValueFilter dateFormat() {
        return (o, key, value) -> {
            if (null != value) {
                if (value instanceof Date) {
                    value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
                }
                if (value instanceof LocalDate) {
                    value = DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) value);
                }
                if (value instanceof LocalDateTime) {
                    value = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format((LocalDateTime) value);
                }
            }
            return value;
        };
    }
}