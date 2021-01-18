package org.litespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DependencyDescriptorTest.class,
        ApplicationContextV4.class,
        ClassPathBeanDefinitionScannerTest.class,
        ClassReaderTest.class,
        MetadataReaderTest.class,
        PackageResourceLoaderTest.class,
        XmlBeanDefinitionReaderTest.class
})
public class V4AllTest {
}
