package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

public class AspectJAfterReturningAdvice implements Advice {

    private Method adviceMethod;
    private Pointcut pc;
    private Object adviceObject;

    public AspectJAfterReturningAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObject = adviceObject;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object o = mi.proceed();
        //调用TransactionManager的commit方法
        adviceMethod.invoke(adviceObject);
        return o;
    }

    @Override
    public Pointcut getPointcut() {
        return pc;
    }

}
