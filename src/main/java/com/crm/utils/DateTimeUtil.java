package com.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 17:19
 */
public class DateTimeUtil {

    public static String getSysTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        return format;
    }
}
