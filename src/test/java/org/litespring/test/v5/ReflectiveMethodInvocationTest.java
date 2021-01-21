package org.litespring.test.v5;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.framework.ReflectiveMethodInvocation;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectiveMethodInvocationTest {

    private AspectJBeforeAdvice beforeAdvice = null;
    private AspectJAfterReturningAdvice afterReturningAdvice = null;
    private PetStoreService petStoreService = null;
    private TransactionManager tx;

    @Before
    public void setup() throws NoSuchMethodException {
        petStoreService = new PetStoreService();
        tx = new TransactionManager();
        MessageTracker.clearMsg();
        beforeAdvice = new AspectJBeforeAdvice(
                TransactionManager.class.getMethod("start"),
                null,
                tx);
        afterReturningAdvice = new AspectJAfterReturningAdvice(
                TransactionManager.class.getMethod("commit"),
                null,
                tx
        );
    }

    @Test
    public void testMethodInvocation() throws Throwable{
        Method targetMethod = PetStoreService.class.getMethod("placeOrder");
        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
        interceptors.add(beforeAdvice);
        interceptors.add(afterReturningAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreService,
                targetMethod,new Object[0],interceptors
                );
        mi.proceed();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3,msgs.size());
        Assert.assertEquals("start tx",msgs.get(0));
        Assert.assertEquals("place order",msgs.get(1));
        Assert.assertEquals("commit tx",msgs.get(2));

    }

}
