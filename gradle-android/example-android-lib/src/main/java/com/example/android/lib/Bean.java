package com.example.android.lib;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mrseasons on 2015/4/28.
 */
public class Bean {

    private String privateField;
    public String publicField;
    String friendlyField;

    private static AtomicInteger count = new AtomicInteger(0);

    public Bean() {
        count.addAndGet(1);
    }

    public static int getCount() {
        return count.get();
    }

    private void privateMethod() {
        System.out.println("privateMethod");
    }

    public void publicMethod() {
        System.out.println("publicMethod");
    }

    void friendlyMethod() {
        System.out.println("friendlyMethod");
    }

    class InnerClass {

    }
}
