package cn.xxywithpq.proxy.jdkproxy.interceptor;

import cn.xxywithpq.proxy.common.Interceptor;
import cn.xxywithpq.proxy.common.Intercepts;
import cn.xxywithpq.proxy.common.Invocation;

@Intercepts(methods = {"method1", "halloAop"})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("进入 intercept");
        Object proceed = invocation.proceed();
        System.out.println("结束 intercept");

        return proceed;
    }
}
