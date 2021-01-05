package org.litespring.beans.factory.config;

public interface SingletonBeanRegistry {
    void registerSingleton(String beanName,Object singletonObjct);

    Object getSingleton(String beanName);
}
