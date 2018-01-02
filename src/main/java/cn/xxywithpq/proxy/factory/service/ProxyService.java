package cn.xxywithpq.proxy.factory.service;

import cn.xxywithpq.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;

/**
 * @author panqian
 */
public interface ProxyService {
    /**
     * 产生AOP对象的具体逻辑
     *
     * @param target
     * @param proxy
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    Object generateAOPObject(Object target, Proxy proxy) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
