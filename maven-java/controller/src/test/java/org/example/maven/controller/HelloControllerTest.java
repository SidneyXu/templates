package org.example.maven.controller;

import junit.framework.TestCase;

/**
 * Created by SidneyXu on 2015/11/27.
 */
public class HelloControllerTest extends TestCase {

    public void testSayHello() throws Exception {
        HelloController controller = new HelloController();
        String result = controller.sayHello();
        assertTrue(result.startsWith("Hello World"));
    }
}