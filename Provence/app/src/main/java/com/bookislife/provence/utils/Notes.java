package com.bookislife.provence.utils;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public enum Notes {

    INSTANCE;

    public static final String NOTES_DATA="notes.json";


    public static void initialize(Context context) throws JSONException {
String jsonString=INSTANCE.readAssets(context,NOTES_DATA);
        JSONObject jsonObject=new JSONObject(jsonString);

    }

    private String readAssets(Context context, String name) {
        InputStream in = null;
        try {
            in = context.getResources().getAssets().open(name);
            StringBuilder stringBuilder = new StringBuilder();
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                stringBuilder.append(new String(buffer, 0, len));
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

}
