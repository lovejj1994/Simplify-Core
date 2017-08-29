package cn.xxywithpq.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 动态代理，新增Interceptor接口，让拦截逻辑分离出来
 * Created by panqian on 2017/7/31.
 */
public class Plugin implements InvocationHandler {

    private Object object;
    private Interceptor interceptor;
    private ArrayList<Method> interceptsList;

    public Plugin(Object object, Interceptor interceptor, ArrayList<Method> interceptsList) {
        this.object = object;
        this.interceptor = interceptor;
        this.interceptsList = interceptsList;
    }

    public static Object wrap(Object target, Interceptor interceptor) {
        ArrayList<Method> interceptsList = getInterceptsList(target, interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return interfaces.length > 0 ? Proxy.newProxyInstance(type.getClassLoader(), interfaces, new Plugin(target, interceptor, interceptsList)) : target;
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
        if (null != interceptsList && interceptsList.contains(method)) {
            return interceptor.intercept(new Invocation(method, args, object));
        }
        return method.invoke(object, args);
    }
}
