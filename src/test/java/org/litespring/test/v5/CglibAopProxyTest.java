package org.litespring.test.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.AopProxyFactory;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.util.List;

public class CglibAopProxyTest extends AbstractV5Test{
    private AspectJBeforeAdvice beforeAdvice = null;
    private AspectJAfterReturningAdvice afterReturningAdvice = null;
    private static AspectJExpressionPointcut pc = null;
    private BeanFactory beanFactory = null;
    private AspectInstanceFactory aspectInstanceFactory = null;


    @Before
    public void setup() throws Exception {
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);
        beanFactory = this.getBeanFactory("petstore-v5.xml");
        aspectInstanceFactory = this.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);

        beforeAdvice = new AspectJBeforeAdvice(
                TransactionManager.class.getMethod("start"),
                pc,
                aspectInstanceFactory);
        afterReturningAdvice = new AspectJAfterReturningAdvice(
                TransactionManager.class.getMethod("commit"),
                pc,
                aspectInstanceFactory
        );
    }

    @Test
    public void testGetProxy() {
        AopConfig config = new AopConfigSupport();
        config.addAdvice(beforeAdvice);
        config.addAdvice(afterReturningAdvice);
        config.setTargetObject(new PetStoreService());

        AopProxyFactory proxyFactory = new CglibProxyFactory(config);
        PetStoreService proxy =  (PetStoreService) proxyFactory.getProxy();
        proxy.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3,msgs.size());
        Assert.assertEquals("start tx",msgs.get(0));
        Assert.assertEquals("place order",msgs.get(1));
        Assert.assertEquals("commit tx",msgs.get(2));
    }

}
