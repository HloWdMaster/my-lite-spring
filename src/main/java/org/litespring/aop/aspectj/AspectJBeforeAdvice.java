package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

public class AspectJBeforeAdvice  extends AbstractAspectJAdvice {


    public AspectJBeforeAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory adviceObjectFactory) throws Exception {
        super(adviceMethod, pc, adviceObjectFactory);
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //调用TransactionManager的start方法commit
        this.invokeAdviceMethod();
        Object o = mi.proceed();
        return o;
    }

}
