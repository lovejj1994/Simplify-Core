package cn.xxywithpq.proxy;

import cn.xxywithpq.proxy.common.Interceptor;
import cn.xxywithpq.proxy.common.Intercepts;
import cn.xxywithpq.proxy.common.Invocation;
import cn.xxywithpq.proxy.factory.ProxyFactory;
import cn.xxywithpq.proxy.factory.service.ProxyService;
import cn.xxywithpq.proxy.factory.service.impl.AsmProxyServiceImpl;
import cn.xxywithpq.proxy.factory.service.impl.JDKProxyServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * 动态代理，新增Interceptor接口，让拦截逻辑分离出来
 * Created by panqian on 2017/7/31.
 */
public class Proxy implements InvocationHandler {

    private static Logger logger = Logger.getLogger(Proxy.class.getName());
    private Object object;
    private Interceptor interceptor;
    private ArrayList<Method> interceptsList;

    public Proxy(Object object, Interceptor interceptor, ArrayList<Method> interceptsList) {
        this.object = object;
        this.interceptor = interceptor;
        this.interceptsList = interceptsList;
    }

    /**
     * 代理的入口
     *
     * @param target      被代理对象
     * @param interceptor 代理逻辑
     * @return 代理类
     */
    public static Object wrap(Object target, Interceptor interceptor) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if (null == interfaces || interfaces.length < 1) {
            return wrap(target, interceptor, false);
        } else {
            return wrap(target, interceptor, true);
        }
    }

    /**
     * 代理的入口
     *
     * @param target      被代理对象
     * @param interceptor 代理逻辑
     * @param isJdkProxy  true 使用jdk ,false asm
     * @return 代理类
     */
    public static Object wrap(Object target, Interceptor interceptor, boolean isJdkProxy) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //收集拦截方法
        ArrayList<Method> interceptsList = getInterceptsList(target, interceptor);
        Proxy proxy = new Proxy(target, interceptor, interceptsList);
//        jdk-proxy
        if (isJdkProxy) {
            return staticserviceConsumer(JDKProxyServiceImpl.factory, proxy, target);
//        asm-proxy
        } else {
            return staticserviceConsumer(AsmProxyServiceImpl.factory, proxy, target);
        }
    }

    public static Object staticserviceConsumer(ProxyFactory proxyFactory, Proxy proxy, Object target) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ProxyService service = proxyFactory.getService();
        return service.generateAOPObject(target, proxy);
    }

    private static ArrayList<Method> getInterceptsList(Object target, Interceptor interceptor) {
        Intercepts interceptsAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
        if (interceptsAnnotation == null) {
            throw new RuntimeException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        }
        ArrayList<Method> signatureMap = new ArrayList<>();
        String[] methods1 = interceptsAnnotation.methods();

        ArrayList<Method> methods2 = new ArrayList<>();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            methods2.addAll(Arrays.asList(interfaces[i].getDeclaredMethods()));
        }

        methods2.addAll(Arrays.asList(target.getClass().getMethods()));

        for (int i = 0; i < methods1.length; i++) {
            String methodName = methods1[i];
            for (int j = 0; j < methods2.size(); j++) {
                if (methods2.get(j).getName().equals(methodName)) {
                    signatureMap.add(methods2.get(j));
                    break;
                }
            }
        }
        return signatureMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        int $simplify = proxy.getClass().getSimpleName().lastIndexOf("$simplify");

        if ($simplify > 0) {
            String substring = method.getName().substring(0, method.getName().lastIndexOf("$simplify"));
            ArrayList<Class> classes = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                classes.add(args[i].getClass());
            }
            Method method1 = object.getClass().getMethod(substring, classes.toArray(new Class[classes.size()]));
            if (null != interceptsList && interceptsList.contains(method1)) {
                return interceptor.intercept(new Invocation(method, args, proxy));
            }
        } else {
            return interceptor.intercept(new Invocation(method, args, object));
        }
        return method.invoke(object, args);
    }
}
