package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitonReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitonReader reader = new XmlBeanDefinitonReader(factory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }


    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    protected abstract Resource getResourceByPath(String path);

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
