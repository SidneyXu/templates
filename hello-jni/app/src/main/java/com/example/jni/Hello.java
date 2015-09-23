package com.example.jni;

/**
 * Created by mrseasons on 2015/09/22.
 */
//http://stackoverflow.com/questions/21096819/jni-and-gradle-in-android-studio
    //http://qiita.com/eaglesakura/items/c4af7989b03904d66ebe

public class Hello {

    static {
        System.loadLibrary("app");
    }

    public native String hellojni();

}
