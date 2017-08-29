package cn.xxywithpq.proxy.jdkProxy;

public interface Interceptor {
    Object intercept(Invocation invocation) throws Throwable;
}