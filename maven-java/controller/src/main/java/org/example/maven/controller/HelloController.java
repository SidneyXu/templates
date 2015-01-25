package org.example.maven.controller;

import org.example.maven.service.HelloService;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

/**
 * Created by mrseasons on 2015/1/25.
 */
public class HelloController {

    private HelloService helloService = new HelloService();

    public void sayHello() {
        PrettyTime prettyTime = new PrettyTime();
        System.out.println("sayHello at " + prettyTime.format(new Date()));

        helloService.sayHello();
    }
}
