package com.bookislife.provence.models;

import com.bookislife.provence.utils.JSONConverter;
import com.bookislife.provence.utils.Md5;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements JSONConverter {

    private static final String JSON_EMAIL = "email";

    private static final String JSON_PASSWORD = "password";

    private static final String JSON_PHOTO = "photo";

    private String email;

    private String md5Password;

    private Photo photo;

    public User(String email, String password) {
        this.email = email;
        this.md5Password = Md5.encode(password);
    }

    public User(JSONObject jsonObject) throws JSONException {
        fromJSON(jsonObject);
    }

    public boolean isMatched(String email, String password) {
        return this.email.equals(email) && md5Password.equals(Md5.encode(password));
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_EMAIL, email);
        jsonObject.put(JSON_PASSWORD, md5Password);
        if (photo != null) {
            jsonObject.put(JSON_PHOTO, photo.toJSON());
        }
        return jsonObject;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        email = jsonObject.getString(JSON_EMAIL);
        md5Password = jsonObject.getString(JSON_PASSWORD);
        if (jsonObject.has(JSON_PHOTO)) {
            photo = new Photo(jsonObject.getJSONObject(JSON_PHOTO));
        }
    }

    public String getEmail() {
        return email;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
