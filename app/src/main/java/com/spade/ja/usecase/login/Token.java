package com.spade.ja.usecase.login;

import com.spade.ja.datalayer.DataRepository;

import javax.inject.Inject;

/**
 * Created by ehab on 12/21/17.
 */

public class Token {

    private DataRepository dataRepository;

    @Inject
    public Token(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public String getToken() {
        return dataRepository.getToken();
    }

    public void clearToken() {
        dataRepository.clearToken();
        dataRepository.clearProfile();
    }
}
