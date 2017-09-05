package cn.xxywithpq.proxy.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Invocation {
    private Method method;
    private Object[] args;
    private Object target;

    public Invocation(Method method, Object[] args, Object target) {
        this.method = method;
        this.args = args;
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(this.target, this.args);
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "method=" + method +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}