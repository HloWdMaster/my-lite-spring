package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    Resource resource = new ClassPathResource("petstore-v1.xml");
    Resource invalidResource = new ClassPathResource("xxx.xml");

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertTrue(bd.isSingleton());

        assertFalse(bd.isPrototype());

        assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

        assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
        PetStoreService petStore =  (PetStoreService)factory.getBean("petStore");
        assertNotNull(petStore);

        PetStoreService petStore1 =  (PetStoreService)factory.getBean("petStore");
        assertTrue(petStore.equals(petStore1));
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions(resource);
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException ");
    }

    @Test
    public void invalidXML() {

        try {
            reader.loadBeanDefinitions(invalidResource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException ");
    }
}
