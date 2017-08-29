package cn.xxywithpq.proxy.asmproxy.interceptor;

public class AsmAopInterceptor {
    public static void beforeInvoke() {
        System.out.println("before");
    }

    ;

    public static void afterInvoke() {
        System.out.println("after");
    }

    ;
}