package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v2.PetStoreService;

public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService service = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(service.getAccountDao());
        Assert.assertNotNull(service.getItemDao());

        Assert.assertTrue(service.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(service.getItemDao() instanceof ItemDao);

        Assert.assertEquals("dadongge", service.getOwner());

    }
}
