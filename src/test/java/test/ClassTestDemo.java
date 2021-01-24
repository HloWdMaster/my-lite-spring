package test;

import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;

public class ClassTestDemo {

    public static void main(String[] args) {
        Class parent = AopConfig.class;
        Class son = AopConfigSupport.class;

        /**
         * isAssignableFrom 测试结论
         * a.isAssignableFrom(b)
         * a是否是b的父Type或者相同Type 是-true 否-false
         */
        System.out.println(parent.isAssignableFrom(son));
        System.out.println(parent.isAssignableFrom(parent));
        System.out.println(son.isAssignableFrom(parent));

    }
}
