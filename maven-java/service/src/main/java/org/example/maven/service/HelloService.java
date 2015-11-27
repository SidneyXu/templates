package org.example.maven.service;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

/**
 * Created by SidneyXu on 2015/1/24.
 */
public class HelloService {

    public String sayHello() {
        PrettyTime time = new PrettyTime();
        return "Hello World at " + time.format(new Date());
    }
}
