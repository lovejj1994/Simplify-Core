package cn.xxywithpq.proxy.jdkProxy;

import cn.xxywithpq.proxy.asmproxy.AopClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 动态代理，新增Interceptor接口，让拦截逻辑分离出来
 * Created by panqian on 2017/7/31.
 */
public class Proxy implements InvocationHandler {

    private static int flag = 0;
    private Object object;
    private Interceptor interceptor;
    private ArrayList<Method> interceptsList;

    public Proxy(Object object, Interceptor interceptor, ArrayList<Method> interceptsList) {
        this.object = object;
        this.interceptor = interceptor;
        this.interceptsList = interceptsList;
    }

    public static Object wrap(Object target, Interceptor interceptor) {
        flag = 0;
        //收集拦截方法
        ArrayList<Method> interceptsList = getInterceptsList(target, interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return interfaces.length > 0 ? java.lang.reflect.Proxy.newProxyInstance(type.getClassLoader(), interfaces, new Proxy(target, interceptor, interceptsList)) : target;
//        包装Proxy
    }

    public static Object newProxyInstance(Object target, Interceptor interceptor) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        flag = 1;
        Proxy proxy = new Proxy(target, interceptor, null);
        Class<?> ams_temp = new AopClassLoader().defineClass(target.getClass(), proxy);
        Object o = ams_temp.newInstance();
        Method[] methods = o.getClass().getMethods();
        Method[] declaredMethods = o.getClass().getDeclaredMethods();
        try {
            Method setInvocationHandler = o.getClass().getDeclaredMethod("setInvocationHandler", Proxy.class);
            setInvocationHandler.setAccessible(true);
            setInvocationHandler.invoke(o, proxy);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        }
        return o;

    }

    private static ArrayList<Method> getInterceptsList(Object target, Interceptor interceptor) {
        Intercepts interceptsAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
        // issue #251
        if (interceptsAnnotation == null) {
            throw new RuntimeException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        }
        ArrayList<Method> signatureMap = new ArrayList<>();
        String[] methods1 = interceptsAnnotation.methods();
//        Method[] methods2 =
        ArrayList<Method> methods2 = new ArrayList<>();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            methods2.addAll(Arrays.asList(interfaces[i].getDeclaredMethods()));
        }

        for (int i = 0; i < methods1.length; i++) {
            String methodName = methods1[0];
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
        if (flag == 1) {
            return interceptor.intercept(new Invocation(method, args, proxy));
        }

        if (null != interceptsList && interceptsList.contains(method)) {
            return interceptor.intercept(new Invocation(method, args, object));
        }
        return method.invoke(object, args);
    }
}
