package com.example.ehab.japroject.datalayer.remote;

import com.example.ehab.japroject.datalayer.pojo.User;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface RemoteSource {

    // all apis in the application
    Single checkUser(String email,String password);

    Single registerUser(User user);
}
