package com.spade.ja.datalayer.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.spade.ja.JaApplication;
import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ehab on 12/2/17.
 */

public class SharedPref<T extends BaseModel> {

    public static final String CATEGORIES = "categories";
    private static final String SHARED_PREF = "ja shared pref";
    public static final String ALL_EVENTS = "all-events";
    public static final String USER = "user";
    public static final String TOKEN = "token";
    public static final String TOP_VENUES = "top venues";
    public static final String ALL_VENUES = "all venues";
    public static final String NEARBY_VENUES = "nearbyVenues";
    private Context context = JaApplication.getContext();
    private SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
    public static final String DATA_KEY = "data-key";
    public static final String TOP_EVENTS = "topEvents";
    public static final String NEARBY_EVENTS = "nearbyEvents";
    public static final String TADAY_EVENTS = "todayEvents";
    public static final String WEEK_EVENTS = "weekEvents";
    public static final String REGISTERED_USER = "registeredUser";
    public static final String PROFILE = "profile";
    public static final String LIKED_EVENTS = "likedEvents";

    public void saveObject(String key, Object object) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, new Gson().toJson(object));
        editor.commit();
    }

    public T getObject(String key, Class<T> modelClass) {
        String s = preferences.getString(key, null);
        Gson gson = new Gson();
        return gson.fromJson(s, modelClass);
    }

    public String getString(String key) {
        return preferences.getString(key,null);
    }

    public void saveString(String key,String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public List<Category> getCategoryList(String key) {
        String s = preferences.getString(key, null);
        Type typeOfObjectsList = new TypeToken<List<Category>>() {
        }.getType();
        List<Category> categoriesList = new Gson().fromJson(s, typeOfObjectsList);
        return categoriesList;
    }

    public void clearToken(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(TOKEN);
        editor.apply();
    }


}
