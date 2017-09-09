package cn.xxywithpq.proxy.factory.service.impl;

import cn.xxywithpq.proxy.Proxy;
import cn.xxywithpq.proxy.factory.ProxyFactory;
import cn.xxywithpq.proxy.factory.service.ProxyService;

/**
 * JDKProxyService
 */
public class JDKProxyServiceImpl implements ProxyService {

    public static ProxyFactory factory = JDKProxyServiceImpl::new;

    private JDKProxyServiceImpl() {
    }

    @Override
    public Object generateAOPObject(Object target, Proxy proxy) throws ClassNotFoundException {
        Class<?> type = target.getClass();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return java.lang.reflect.Proxy.newProxyInstance(type.getClassLoader(), interfaces, proxy);
    }
}
