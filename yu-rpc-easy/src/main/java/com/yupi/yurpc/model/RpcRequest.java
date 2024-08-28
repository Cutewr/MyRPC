package com.yupi.yurpc.model;

import com.yupi.yurpc.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
* 封装 调用所需要的信息。方便之后反射实现调用
* */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    private String serviceName;
    private String methodName;
    /*
    * 参数类型列表
    * */
    private Class<?>[] parameterTypes;

    /*
    * 参数列表
    * */
    private Object[] args;

}
