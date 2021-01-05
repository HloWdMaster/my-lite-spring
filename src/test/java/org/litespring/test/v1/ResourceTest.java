package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.File;
import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception {
        Resource r = new ClassPathResource("petstore-v1.xml");

        InputStream is = null;

        try {
            is = r.getInputStream();
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @Test
    public void testFileSystemResource() throws Exception {

        String absPath = ClassLoader.getSystemResource("petstore-v1.xml").getPath();
        Resource r = new FileSystemResource(absPath);

		InputStream is = null;

		try {
			is = r.getInputStream();
			// 注意：这个测试其实并不充分！！
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
    }
}
