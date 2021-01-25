package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice{

    public AspectJAfterThrowingAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory adviceObjectFactory) {
        super(adviceMethod, pc, adviceObjectFactory);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable throwable) {
            invokeAdviceMethod();
            throw throwable;
        }
    }
}
