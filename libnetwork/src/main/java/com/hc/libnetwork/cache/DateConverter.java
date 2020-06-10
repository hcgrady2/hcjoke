package com.hc.libnetwork.cache;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by hcw  on 2020/6/4
 * 类描述：
 * all rights reserved
 */
public class DateConverter {


    /**
     *加上注解，选择枚举，进行自动类型转换
     */
    @TypeConverter
    public static Long date2Long(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date long2Date(Long data) {
        return new Date(data);
    }
}
