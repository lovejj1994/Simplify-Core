package cn.xxywithpq;

import cn.xxywithpq.proxy.asmproxy.AopClassLoader;
import cn.xxywithpq.proxy.jdkProxy.Interceptor;
import cn.xxywithpq.proxy.jdkProxy.Plugin;
import cn.xxywithpq.proxy.bean.Source;
import cn.xxywithpq.proxy.bean.Sourceable;
import cn.xxywithpq.proxy.interceptor.MyInterceptor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author panqian
 */
public class JdkProxyTest {

    @Test
    void jdkProxy01() {

        System.out.println("===================JdkProxyTest01===================");

        Sourceable source = new Source();

        //lambda表达式貌似不能加注解，所以换成传统实现类
        Interceptor interceptor = new MyInterceptor();

        Sourceable sourceable = (Sourceable) Plugin.wrap(source, interceptor);

        sourceable.method();
        System.out.println("=========");
        sourceable.method1(666);
    }


    @Test
    void jdkProxy02() {

        System.out.println("===================JdkProxyTest02===================");
        try {
            Class<?> asm_tmp = new AopClassLoader(ClassLoader.getSystemClassLoader()).loadClass("cn.xxywithpq.proxy.asmproxy.bean.Asm_Tmp");
            try {
                Method method = asm_tmp.getMethod("halloAop");
                Object invoke = method.invoke(asm_tmp.newInstance());

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        AopClassAdapter aopClassAdapter = new AopClassAdapter();
    }
}
