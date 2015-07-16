package com.bookislife.provence.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastHelper {

    public static ToastBuilder with(Context context) {
        return new ToastBuilder(context);
    }

    public static class ToastBuilder {

        private Context context;
        private String msg;
        private int duration;

        public ToastBuilder(Context context) {
            this.context = context;
            duration = Toast.LENGTH_SHORT;
        }

        public ToastBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public void show() {
            Toast.makeText(context, msg, duration).show();
        }

        public void show(String msg) {
            Toast.makeText(context, msg, duration).show();
        }

        public void show(int msgId) {
            Toast.makeText(context, context.getString(msgId), duration).show();
        }

        public ToastBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }
    }

}
