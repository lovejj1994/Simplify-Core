package cn.xxywithpq;

import cn.xxywithpq.proxy.asmproxy.bean.Asm;
import cn.xxywithpq.proxy.asmproxy.bean.Asm1;
import cn.xxywithpq.proxy.jdkProxy.Interceptor;
import cn.xxywithpq.proxy.jdkProxy.Proxy;
import cn.xxywithpq.proxy.jdkproxy.bean.Source;
import cn.xxywithpq.proxy.jdkproxy.bean.Sourceable;
import cn.xxywithpq.proxy.jdkproxy.interceptor.MyInterceptor;
import org.junit.jupiter.api.Test;

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

        Sourceable sourceable = (Sourceable) Proxy.wrap(source, interceptor);

        sourceable.method();
        System.out.println("=========");
        sourceable.method1(666);
    }


    @Test
    void jdkProxy02() {

        System.out.println("===================JdkProxyTest02===================");
        try {
            Asm asm = new Asm();
            MyInterceptor myInterceptor = new MyInterceptor();
            Asm asm_tmp = null;
            try {
                asm_tmp = (Asm) Proxy.newProxyInstance(asm, myInterceptor);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
//                Method method = asm_tmp.getMethod("halloAop");
//                Constructor<?> declaredConstructor = asm_tmp.getDeclaredConstructor(new Class[]{int.class});
//                Object o = declaredConstructor.newInstance(new Object[]{1});
//                Object invoke = method.invoke(o);
            asm_tmp.halloAop();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
//        AopClassAdapter aopClassAdapter = new AopClassAdapter();
    }


    @Test
    void jdkProxy03() {
        Asm asm = new Asm();
        Asm1 asm1 = (Asm1) asm;
        asm1.halloAop();
        System.out.println("===================JdkProxyTest03===================");

    }

}
