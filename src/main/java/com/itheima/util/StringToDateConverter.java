package com.itheima.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串转日期类型
 */
public class StringToDateConverter implements Converter<String, Date> {
    public Date convert(String source) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("数据类型转换异常");
        }
    }
}