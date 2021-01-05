package org.litespring.context.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitonReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public FileSystemXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitonReader reader = new XmlBeanDefinitonReader(factory);
        Resource resource = new FileSystemResource(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return factory.getBeanDefinition(beanID);
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
}
