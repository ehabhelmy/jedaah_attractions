package com.example.ehab.japroject.usecase.splash;

import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.authentication.Unsubscribable;

/**
 * Created by ehab on 12/2/17.
 */

public interface SplashUseCaseInterface extends Unsubscribable{
    void checkUserInCache(BaseCallback<User> userBaseCallback);
}
