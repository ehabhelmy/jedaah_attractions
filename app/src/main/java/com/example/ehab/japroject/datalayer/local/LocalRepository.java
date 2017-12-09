package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.User;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public class LocalRepository implements LocalSource {

    private SharedPref sharedPref;

    @Inject
    public LocalRepository(SharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    public Single<User> checkUser() {
        User user = (User) sharedPref.getObject("asdas", User.class);
        Single<User> userSingle = Single.create(e -> {
            if (user == null) {
            e.onError(null);
            }else {
                e.onSuccess(user);
            }
        });
        return userSingle;
    }
}
