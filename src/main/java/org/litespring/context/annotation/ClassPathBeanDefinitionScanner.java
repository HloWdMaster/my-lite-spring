package org.litespring.context.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.BeanNameGenerator;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;
import org.litespring.util.StringUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathBeanDefinitionScanner {
    private BeanDefinitionRegistry registry;
    private PackageResourceLoader resourceLoader = new PackageResourceLoader();
    protected final Log logger = LogFactory.getLog(getClass());

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public Set<BeanDefinition> doScan(String packagesToScan) {
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan,",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates =  findCandidataComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getID(),candidate);
            }
        }
        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidataComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        try {
            Resource[] resources = this.resourceLoader.getResource(basePackage);

            for (Resource resource : resources) {

                try {
                    MetadataReader metadataReader = new SimpleMetadataReader(resource);
                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(sbd,this.registry);
                        sbd.setId(beanName);
                        candidates.add(sbd);
                    }
                } catch (Throwable e) {
                    throw new BeanDefinitionStoreException(
                            "Failed to read candidate component class: "+resource,e);
                }
            }
            
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning",e);
        }
        return candidates;
    }



}
