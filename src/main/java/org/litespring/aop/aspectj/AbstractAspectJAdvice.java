package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice implements Advice {
    private Method adviceMethod;
    private Pointcut pc;
    private Object adviceObject;

    public AbstractAspectJAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObject = adviceObject;
    }

    public void invokeAdviceMethod() throws Throwable{
        adviceMethod.invoke(adviceObject);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pc;
    }

}
