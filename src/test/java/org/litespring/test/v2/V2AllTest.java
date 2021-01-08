package org.litespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.ApplicationContextTest;
import org.litespring.test.v1.BeanFactoryTest;
import org.litespring.test.v1.ResourceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest.class
})
public class V2AllTest {
}
