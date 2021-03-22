package com.crm.utils;

import java.util.UUID;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 17:28
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
