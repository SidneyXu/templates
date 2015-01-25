package org.example.maven.service;

import junit.framework.TestCase;

public class HelloServiceTest extends TestCase {

    public void testSayHello() throws Exception {
        HelloService service = new HelloService();
        String result = service.sayHello();
        assertTrue(result.startsWith("Hello World"));
    }
}