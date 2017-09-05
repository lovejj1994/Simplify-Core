package cn.xxywithpq.proxy.asmproxy.bean;

import cn.xxywithpq.proxy.Proxy;

import java.lang.reflect.Method;

public class test {
    private Proxy proxy;

    protected void setInvocationHandler(Proxy proxy) {
        this.proxy = proxy;
    }

    public void halloAop() {
        try {
            Class[] var2 = new Class[0];
            Object[] var3 = new Object[0];
            Method var4 = this.getClass().getMethod("halloAop$simplify", var2);
            Object var5 = this.proxy.invoke(this, var4, var3);
        } catch (Throwable var8) {
            throw new RuntimeException(var8);
        }
    }
}
