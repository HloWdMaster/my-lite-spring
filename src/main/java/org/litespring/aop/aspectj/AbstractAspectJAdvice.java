package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice implements Advice {
    private Method adviceMethod;
    private Pointcut pc;
    private AspectInstanceFactory adviceObjectFactory;

    public AbstractAspectJAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory adviceObjectFactory) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObjectFactory = adviceObjectFactory;
    }

    public void invokeAdviceMethod() throws Throwable{
        adviceMethod.invoke(adviceObjectFactory.getAspectInstance());
    }

    @Override
    public Pointcut getPointcut() {
        return this.pc;
    }

}
