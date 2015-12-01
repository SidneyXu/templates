package com.example.jni;

/**
 * Created by SidneyXu on 2015/09/22.
 */
public class Hello {

    static {
        System.loadLibrary("app");
    }

    public native String hellojni();

}
