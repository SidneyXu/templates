package com.bookislife.provence.models;

import com.bookislife.provence.utils.JSONConverter;

import org.json.JSONException;
import org.json.JSONObject;


public class Photo implements JSONConverter {

    private static final String JSON_FILENAME = "fileName";

    private String fileName;

    public Photo(String fileName) {
        this.fileName = fileName;
    }

    public Photo(JSONObject jsonObject) throws JSONException {
        fromJSON(jsonObject);
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_FILENAME, fileName);
        return jsonObject;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        fileName = jsonObject.getString(JSON_FILENAME);
    }

    public String getFileName() {
        return fileName;
    }
}
