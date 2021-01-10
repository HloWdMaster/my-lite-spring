package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitonReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

public class BeanDefinitionTestV3 {

    @Test
    public void testConstructorArgument() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitonReader reader = new XmlBeanDefinitonReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStore");
        ConstructorArgument args = bd.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolders =  args.getArgumentValues();

        Assert.assertEquals(3,valueHolders.size());

        RuntimeBeanReference ref1 = (RuntimeBeanReference) valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao",ref1.getBeanName());
        RuntimeBeanReference ref2 = (RuntimeBeanReference)valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao",ref2.getBeanName());
        TypedStringValue  strValue = (TypedStringValue) valueHolders.get(2).getValue();
        Assert.assertEquals("1",strValue.getValue());
    }
}
