package com.yupi.example.provider;

import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.registry.LocalRegistry;
import com.yupi.yurpc.server.HttpServer;
import com.yupi.yurpc.server.VertxHttpServer;

/*
* 服务提供者启动类
* */
public class EasyProviderExample {
    public static void main(String[] args) {
        // provider启动会，需要注册到注册中心
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        // 启动web服务
        HttpServer httpServer=new VertxHttpServer();
        httpServer.doStart(8080);

    }
}
