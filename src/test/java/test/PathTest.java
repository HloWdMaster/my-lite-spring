package test;

import java.io.File;

public class PathTest {

    public static void main(String[] args) {
        System.out.println(PathTest.class.getResource("").getPath());
        System.out.println(PathTest.class.getResource("/petstore-v1.xml").getPath());
        System.out.println(new File(ClassLoader.getSystemResource("petstore-v1.xml").getPath()).getAbsolutePath());
        System.out.println(new File("").getAbsolutePath());
    }
}
