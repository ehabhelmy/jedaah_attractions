package com.example.ehab.japroject.datalayer.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
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
    private Context context = JaApplication.getContext();
    private SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
    public static final String DATA_KEY = "data-key";
    public static final String TOP_EVENTS = "topEvents";
    public static final String NEARBY_EVENTS = "nearbyEvents";

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

    public List<Category> getCategoryList(String key) {
        String s = preferences.getString(key, null);
        Type typeOfObjectsList = new TypeToken<List<Category>>() {
        }.getType();
        List<Category> categoriesList = new Gson().fromJson(s, typeOfObjectsList);
        return categoriesList;
    }


}
