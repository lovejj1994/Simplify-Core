package cn.xxywithpq.proxy.factory;

import cn.xxywithpq.proxy.factory.service.ProxyService;

public interface ProxyFactory {
    ProxyService getService();
}
