package org.litespring.test.v1;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;
import org.junit.Assert;

public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystem() {
        String absPath = ClassLoader.getSystemResource("petstore-v1.xml").getPath();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(absPath);
        PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
