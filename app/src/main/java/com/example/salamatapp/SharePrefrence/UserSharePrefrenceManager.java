package com.example.salamatapp.SharePrefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.salamatapp.DomainLayer.SaveUserItem;

public class UserSharePrefrenceManager {

    private static final String USER_SHARED_PREF_NAME = "user_shared_pref";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_IS_ACTIVE = "active";
    private static final String KEY_User_Id = "userid";
    private static final String KEY_User_Type = "type";
    private static final String KEY_First_Time = "first";
    private SharedPreferences sharedPreferences;

    public UserSharePrefrenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(SaveUserItem user) {
        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAME, user.getName());
            editor.putString(KEY_NUMBER, user.getNumber());
            editor.putString(KEY_PASSWORD, user.getPassword());
            editor.putString(KEY_IMAGE, user.getImage());
            editor.putString(KEY_User_Id, user.getUserId());
            editor.putBoolean(KEY_IS_ACTIVE, user.isIs_Active());
            editor.putInt(KEY_User_Type, user.getType());
            editor.putInt(KEY_First_Time, user.getFirstTime());
            editor.apply();
        }
    }

    public SaveUserItem getUser() {
        SaveUserItem user = new SaveUserItem();
        user.setName(sharedPreferences.getString(KEY_NAME, ""));
        user.setNumber(sharedPreferences.getString(KEY_NUMBER, ""));
        user.setPassword(sharedPreferences.getString(KEY_PASSWORD, ""));
        user.setImage(sharedPreferences.getString(KEY_IMAGE, ""));
        user.setUserId(sharedPreferences.getString(KEY_User_Id, ""));
        user.setIs_Active(sharedPreferences.getBoolean(KEY_IS_ACTIVE, false));
        user.setType(sharedPreferences.getInt(KEY_User_Type, 0));
        user.setFirstTime(sharedPreferences.getInt(KEY_First_Time, 0));
        return user;
    }
}
