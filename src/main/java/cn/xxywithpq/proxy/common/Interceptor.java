package cn.xxywithpq.proxy.common;

public interface Interceptor {
    Object intercept(Invocation invocation) throws Throwable;
}