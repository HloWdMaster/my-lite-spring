package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;
    private Class<?> beanClass;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;
    private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
    private ConstructorArgument  constructorArgument = new ConstructorArgument();
    //表明这个Bean定义是不是我们自己合成的
    private boolean isSynthetic = false;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public GenericBeanDefinition(Class<?> clz) {
        this.beanClass = clz;
        this.beanClassName = clz.getName();
    }

    public GenericBeanDefinition() {

    }

    public void setSynthetic(boolean isSynthetic) {
        this.isSynthetic = isSynthetic;
    }

    public void setBeanClassName(String className) {
        this.beanClassName = className;
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        //scope为单例或默认值 single为true
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgument.isEmpty();
    }

    @Override
    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
        String className = getBeanClassName();
        if (className == null) {
            return null;
        }
        Class<?> resolvedClass = classLoader.loadClass(className);
        this.beanClass = resolvedClass;
        return resolvedClass;
    }

    @Override
    public Class<?> getBeanClass() throws IllegalStateException {
        if (this.beanClass == null) {
            throw new IllegalStateException(
                    "Bean class name ["+this.getBeanClassName()+"] has not been resolved into an actual Class"
            );
        }
        return this.beanClass;
    }

    @Override
    public boolean hasBeanClass() {
        return this.beanClass != null;
    }

    @Override
    public boolean isSynthetic() {
        return this.isSynthetic;
    }

    public void setId(String id) {
        this.id = id;
    }
}
