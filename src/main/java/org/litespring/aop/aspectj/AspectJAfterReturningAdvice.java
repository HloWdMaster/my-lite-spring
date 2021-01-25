package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice {


    public AspectJAfterReturningAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory adviceObjectFactory) {
        super(adviceMethod, pc, adviceObjectFactory);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object o = mi.proceed();
        //调用TransactionManager的commit方法
        this.invokeAdviceMethod();
        return o;
    }


}
