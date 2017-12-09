package com.example.ehab.japroject.datalayer.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.Gson;

/**
 * Created by ehab on 12/2/17.
 */

public class SharedPref<T extends BaseModel>{

    private static final String SHARED_PREF = "ja shared pref";
    private Context context = JaApplication.getContext();
    private SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);

    public void saveObject(String key,Object object){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,new Gson().toJson(object));
        editor.commit();
    }
    public T getObject(String key, Class<T> modelClass){
        String s = preferences.getString(key,null);
        Gson gson = new Gson();
        return gson.fromJson(s,modelClass);
    }
}
