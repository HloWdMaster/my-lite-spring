package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

public class AspectJBeforeAdvice  extends AbstractAspectJAdvice {


    public AspectJBeforeAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        super(adviceMethod, pc, adviceObject);
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //调用TransactionManager的start方法commit
        this.invokeAdviceMethod();
        Object o = mi.proceed();
        return o;
    }

}
