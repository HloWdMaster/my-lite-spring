package org.litespring.beans.factory;

import org.litespring.aop.Advice;

import java.util.List;

public interface BeanFactory {

    Object getBean(String beanID) ;

    Class<?> getType(String targetBeanName) throws NoSuchBeanDefinitionException;

    List<Object> getBeansByType(Class<?> type);
}
