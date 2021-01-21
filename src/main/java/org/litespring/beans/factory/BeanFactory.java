package org.litespring.beans.factory;

public interface BeanFactory {

    Object getBean(String beanID) ;

    Class<?> getType(String targetBeanName) throws NoSuchBeanDefinitionException;
}
