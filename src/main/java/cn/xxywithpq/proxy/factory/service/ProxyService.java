package cn.xxywithpq.proxy.factory.service;

import cn.xxywithpq.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;

public interface ProxyService {
    Object generateAOPObject(Object target, Proxy proxy) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
