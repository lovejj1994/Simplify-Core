package cn.xxywithpq.proxy.interceptor;

import cn.xxywithpq.proxy.jdkProxy.Interceptor;
import cn.xxywithpq.proxy.jdkProxy.Intercepts;
import cn.xxywithpq.proxy.jdkProxy.Invocation;

@Intercepts(methods = {"method1"})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("进入 intercept");
        invocation.proceed();
        System.out.println("结束 intercept");

        return null;
    }
}