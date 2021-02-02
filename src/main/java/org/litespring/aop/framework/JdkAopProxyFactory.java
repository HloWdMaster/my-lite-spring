package org.litespring.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.aop.Advice;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class JdkAopProxyFactory implements AopProxyFactory, InvocationHandler {
    private static final Log logger = LogFactory.getLog(JdkAopProxyFactory.class);

    private AopConfig config;
    private Object proxy;
    private Method method;
    private Object[] args;

    public JdkAopProxyFactory(AopConfig config) {
        Assert.notNull(config, "AdvisedSupport must not be null");
        if (config.getAdvices().size() == 0) {
            throw new AopConfigException("No Advices specified");
        }
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(ClassUtils.getDefaultClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating JDK dynamic proxy: target source is " + this.config.getTargetObject());
        }
        Class<?>[] proxiedInterfaces = config.getProxiedInterfaces();

        return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object target = this.config.getTargetObject();
        Object retVal;

        List<Advice> chain = this.config.getAdvices(method);
        if (chain.isEmpty()) {
            retVal = method.invoke(target, args);
        } else {
            List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
            interceptors.addAll(chain);
            retVal = new ReflectiveMethodInvocation(target,method,args,interceptors).proceed();
        }
        return retVal;
    }

}
