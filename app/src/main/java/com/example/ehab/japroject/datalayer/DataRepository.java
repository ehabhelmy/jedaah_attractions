package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.local.LocalRepository;
import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;
import com.example.ehab.japroject.datalayer.remote.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public class DataRepository implements DataSource {

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Single<UserResponse> login(String email, String password) {
        // logic to get it from local repository or remote
        return null;
    }

    @Override
    public Single<UserResponse> register() {
        // logic to get it from local repository or remote
        return null;
    }

    @Override
    public Single<User> checkUser() {
        return localRepository.checkUser();
    }
}
