package com.bookislife.provence.models;

import com.bookislife.provence.utils.JSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;


public class Note implements JSONConverter {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_CONTENT = "content";
    private static final String JSON_CREATED_AT = "createdAt";
    private static final String JSON_DELETED_AT = "deletedAt";
    private static final String JSON_CATEGORY = "category";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_TAG = "tag";
    private static final String JSON_IS_DELETED = "isDeleted";
    private static final String JSON_FAVOURITE = "favourite";

    private String id;

    private String title;

    private String content;

    private Date createdAt;

    private Date deletedAt;

    private String category;

    private Photo photo;

    private boolean isDeleted;

    private JSONArray tag;

    private boolean isFavourite;

    public Note() {
        this(null, null);
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        id = UUID.randomUUID().toString();
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, id);
        jsonObject.put(JSON_TITLE, title);
        jsonObject.put(JSON_CONTENT, content);
        jsonObject.put(JSON_CREATED_AT, createdAt.getTime());
        jsonObject.put(JSON_CATEGORY, category);
        if (photo != null) {
            jsonObject.put(JSON_PHOTO, photo.toJSON());
        }
        jsonObject.put(JSON_TAG, tag);
        jsonObject.put(JSON_IS_DELETED, isDeleted);
        if (deletedAt != null) {
            jsonObject.put(JSON_DELETED_AT, deletedAt.getTime());
        }
        jsonObject.put(JSON_FAVOURITE, isFavourite);
        return jsonObject;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getString(JSON_ID);
        title = jsonObject.getString(JSON_TITLE);
        content = jsonObject.getString(JSON_CONTENT);
        createdAt = new Date(jsonObject.getLong(JSON_CREATED_AT));
        photo = new Photo(jsonObject.getJSONObject(JSON_PHOTO));
        category = jsonObject.getString(JSON_CATEGORY);
        tag = jsonObject.getJSONArray(JSON_TAG);
        isDeleted = jsonObject.getBoolean(JSON_IS_DELETED);
        if (jsonObject.has(JSON_DELETED_AT)) {
            deletedAt = new Date(jsonObject.getLong(JSON_DELETED_AT));
        }
        isFavourite = jsonObject.getBoolean(JSON_FAVOURITE);
    }

    @Override
    public String toString() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public JSONArray getTag() {
        return tag;
    }

    public void setTag(JSONArray tag) {
        this.tag = tag;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void addTag(String t) {
        if (tag == null) tag = new JSONArray();
        int size = tag.length();
        for (int i = 0; i < size; i++) {
            if (t.equals(tag.optString(i)))
                return;
        }
        tag.put(t);
    }
}