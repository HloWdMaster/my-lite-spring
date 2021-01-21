package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

public class AspectJBeforeAdvice  implements Advice {
    private Method adviceMethod;
    private Pointcut pc;
    private Object adviceObject;

    public AspectJBeforeAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObject = adviceObject;
    }

    @Override
    public Pointcut getPointcut() {
        return pc;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //调用TransactionManager的start方法commit
        adviceMethod.invoke(adviceObject);
        Object o = mi.proceed();
        return o;
    }

}
