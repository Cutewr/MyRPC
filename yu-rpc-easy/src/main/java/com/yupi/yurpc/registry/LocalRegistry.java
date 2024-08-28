package com.yupi.yurpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegistry {
    /*
    * 使用线程安全的concurrentHashMap来存储注册信息
    * key为服务名称 value为服务的实现类
    * */
    private static Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /*
    * 服务注册
    * */
    public static void register(String serviceName,Class<?> implClass){
        map.put(serviceName,implClass);
    }

    /*
    * 服务获取
    * */
    public static Class<?> get(String serviceName){
        return map.get(serviceName);
    }

    /*
     * 服务删除
     * */
    public static void remove(String serviceName){
        map.remove(serviceName);
    }

}
