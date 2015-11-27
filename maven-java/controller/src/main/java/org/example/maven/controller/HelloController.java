package org.example.maven.controller;

import org.example.maven.service.HelloService;

/**
 * Created by SidneyXu on 2015/1/25.
 */
public class HelloController {

    private HelloService helloService = new HelloService();

    public String sayHello() {
        return helloService.sayHello();
    }
}
