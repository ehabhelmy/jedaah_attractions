package com.example.ehab.japroject.usecase.authentication.login;

import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.authentication.Unsubscribable;

/**
 * Created by ehab on 12/2/17.
 */

public interface LoginUseCaseInterface extends Unsubscribable {
    void login(String email, String password, BaseCallback<UserResponse> baseCallback);
}
