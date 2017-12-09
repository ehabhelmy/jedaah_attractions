package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.User;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface LocalSource {
    // all apis cached in the application

    Single<User> checkUser();
}
