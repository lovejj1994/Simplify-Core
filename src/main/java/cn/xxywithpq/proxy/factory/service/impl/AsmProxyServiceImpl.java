package cn.xxywithpq.proxy.factory.service.impl;

import cn.xxywithpq.proxy.Proxy;
import cn.xxywithpq.proxy.asmproxy.AopClassLoader;
import cn.xxywithpq.proxy.factory.ProxyFactory;
import cn.xxywithpq.proxy.factory.service.ProxyService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * asmProxyService
 */
public class AsmProxyServiceImpl implements ProxyService {

    public static ProxyFactory factory = AsmProxyServiceImpl::new;


    private AsmProxyServiceImpl() {
    }

    @Override
    public Object generateAOPObject(Object target, Proxy proxy) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> ams_temp = new AopClassLoader().defineClass(target.getClass(), proxy);
        Object o = ams_temp.newInstance();
        Method setInvocationHandler = o.getClass().getDeclaredMethod("setInvocationHandler", Proxy.class);
        setInvocationHandler.setAccessible(true);
        setInvocationHandler.invoke(o, proxy);
        return o;
    }
}
