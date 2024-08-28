package com.yupi.yurpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.model.RpcRequest;
import com.yupi.yurpc.model.RpcResponse;
import com.yupi.yurpc.serializer.JdkSerializer;
import com.yupi.yurpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列器
        Serializer serializer=new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest= RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try{
            byte []bodyBytes=serializer.serialize(rpcRequest);
            byte []result;
            // 发送请求
            // TODO 这里地址被硬编码了，需要通过注册中心和服务发现机制解决
            try(HttpResponse httpResponse= HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                result= httpResponse.bodyBytes();
            }
            // 反序列化得到response
            RpcResponse rpcResponse=serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
