package com.example.android.lib;

/**
 * Created by mrseasons on 2015/4/28.
 */
public class Bean {

    private String privateField;
    public String publicField;
    String friendlyField;
    private int count;

    public Bean() {
        count = 0;
    }

    public Bean(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
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
