package org.litespring.core.type.classreading;

import jdk.internal.org.objectweb.asm.Type;
import org.litespring.core.annotation.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor{
    private final Set<String> annotationSet = new LinkedHashSet<String>();
    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<String, AnnotationAttributes>();

    public AnnotationMetadataReadingVisitor(){}

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className,this.attributeMap);
    }

    public Set<String> getAnnotationTypes(){
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getaAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }
}
