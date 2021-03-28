package com.crm.utils;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 20:16
 */
public class ServiceFactory {

    public static Object getService(Object service){

        return new TransactionInvocationHandler(service).getProxy();
    }
}
