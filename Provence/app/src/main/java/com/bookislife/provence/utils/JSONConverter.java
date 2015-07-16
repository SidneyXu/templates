package com.bookislife.provence.utils;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONConverter {

    JSONObject toJSON() throws JSONException;

    void fromJSON(JSONObject jsonObject) throws JSONException;
}
