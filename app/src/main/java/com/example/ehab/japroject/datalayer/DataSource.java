package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<UserResponse> login(String email, String password);

    Single<UserResponse> register();

    Single<User> checkUser();
}
