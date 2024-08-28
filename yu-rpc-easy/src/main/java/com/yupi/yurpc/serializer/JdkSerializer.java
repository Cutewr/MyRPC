package com.yupi.yurpc.serializer;

import java.io.*;

public class JdkSerializer implements Serializer{
    /*
    * 序列化
    * */
    @Override
    public <T> byte[] serialize(T objext) throws IOException {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(objext);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /*
    * 反序列化
    * */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        try {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            objectInputStream.close();
        }
    }
}
