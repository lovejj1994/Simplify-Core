package cn.xxywithpq.proxy.factory;

import cn.xxywithpq.proxy.factory.service.ProxyService;

/**
 * @author panqian
 */
public interface ProxyFactory {
    /**
     * 选择asm还是jdkproxy实现方法
     *
     * @return
     */
    ProxyService getService();
}
