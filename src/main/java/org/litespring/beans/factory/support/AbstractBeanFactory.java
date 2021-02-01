package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;

/**
 * 拿走DefaultBeanFactory的部分功能,使DefaultBeanFactory暴露更少的方法
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
	protected abstract Object createBean(BeanDefinition bd) throws BeanCreationException;
}
