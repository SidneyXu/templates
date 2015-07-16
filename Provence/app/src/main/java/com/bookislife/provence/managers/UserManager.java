package com.bookislife.provence.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bookislife.provence.AppException;
import com.bookislife.provence.R;
import com.bookislife.provence.models.User;
import com.bookislife.provence.utils.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class UserManager {

    private static final String PREF_USER_INFO = "user_info";

    private final Context context;
    private final SharedPreferences preferences;

    private static User currentUser;

    public UserManager(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void login(String username, String password) throws AppException {
        JSONObject jsonObject = PreferencesUtils.loadJSON(preferences, PREF_USER_INFO);
        if (jsonObject == null) {
            throw new AppException(AppException.USER_NOT_EXIST,
                    context.getString(R.string.err_msg_user_not_exist));
        }
        try {
            User localUser = new User(jsonObject);
            throw localUser.isMatched(username, password) ? null :
                    new AppException(AppException.USERNAME_OR_PASSWORD_MISMATCH,
                            context.getString(R.string.err_msg_login_failed));
        } catch (JSONException e) {
            throw new AppException(AppException.USER_NOT_EXIST,
                    context.getString(R.string.err_msg_user_not_exist));
        }
    }

    public void register(String username, String password) throws AppException {
        User user = new User(username, password);
        try {
            PreferencesUtils.putJSON(preferences, PREF_USER_INFO, user.toJSON());
        } catch (JSONException e) {
            throw new AppException(AppException.UNKOWN_EXCEPTION,
                    context.getString(R.string.err_msg_register_failed));
        }
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }
        JSONObject jsonObject = PreferencesUtils.loadJSON(preferences, PREF_USER_INFO);
        if (jsonObject == null)
            return null;

        try {
            currentUser = new User(jsonObject);
            return currentUser;
        } catch (JSONException e) {
            return null;
        }
    }

}
