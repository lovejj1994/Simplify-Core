package cn.xxywithpq;

import cn.xxywithpq.proxy.Proxy;
import cn.xxywithpq.proxy.asmproxy.bean.Asm;
import cn.xxywithpq.proxy.common.Interceptor;
import cn.xxywithpq.proxy.jdkproxy.bean.Source;
import cn.xxywithpq.proxy.jdkproxy.bean.Sourceable;
import cn.xxywithpq.proxy.jdkproxy.interceptor.MyInterceptor;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author panqian
 */
public class ProxyTest {

    @Test
    void proxy01() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        System.out.println("===================proxyTest01 jdkProxy===================");

        Sourceable source = new Source();

        //lambda表达式貌似不能加注解，所以换成传统实现类
        Interceptor interceptor = new MyInterceptor();

        Sourceable sourceable = (Sourceable) Proxy.wrap(source, interceptor);

        sourceable.method();
        System.out.println("=========");
        sourceable.method1(666);
    }


    @RepeatedTest(10)
    void proxy02() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        System.out.println("===================proxyTest02 asmProxy===================");
        try {
            Asm asm = new Asm();
            MyInterceptor myInterceptor = new MyInterceptor();
            Asm asm_tmp = null;
            asm_tmp = (Asm) Proxy.wrap(asm, myInterceptor);
            asm_tmp.halloAop();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
//        AopClassAdapter aopClassAdapter = new AopClassAdapter();
    }

    @Test
    public void getInterface() {
        Class<? super Asm> superclass = Asm.class.getSuperclass();
        Class<?>[] interfaces = Asm.class.getInterfaces();

        System.out.println(interfaces);
    }
}
