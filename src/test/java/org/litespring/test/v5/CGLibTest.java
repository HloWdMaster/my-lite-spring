package org.litespring.test.v5;

import org.junit.Test;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class CGLibTest {

    @Test
    public void testCallback() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PetStoreService.class);
        enhancer.setCallback(new TransactionInterceptor());
        PetStoreService petStore = (PetStoreService) enhancer.create();
        petStore.placeOrder();
        petStore.toString();
    }

    public static class TransactionInterceptor implements MethodInterceptor {
        TransactionManager txManager = new TransactionManager();
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            txManager.start();
            Object result = proxy.invokeSuper(obj, args);
            txManager.commit();

            return result;
        }
    }

    @Test
    public void testFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PetStoreService.class);

        enhancer.setInterceptDuringConstruction(false);
        Callback[] callbacks = {new TransactionInterceptor(), NoOp.INSTANCE};
        Class<?>[] types = new Class<?>[callbacks.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = callbacks[i].getClass();
        }
        enhancer.setCallbackFilter(new ProxyCallbackFilter());
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);
        PetStoreService petStore = (PetStoreService) enhancer.create();
        petStore.placeOrder();
        System.out.println(petStore.toString());
    }

    private static class ProxyCallbackFilter implements CallbackFilter {

        public ProxyCallbackFilter() {
        }

        @Override
        public int accept(Method method) {
            if (method.getName().startsWith("place")) {
                return 0;
            }else{
                return 1;
            }

        }
    }

}
