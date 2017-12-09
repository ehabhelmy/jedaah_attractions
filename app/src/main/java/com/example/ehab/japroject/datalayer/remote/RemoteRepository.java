package com.example.ehab.japroject.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.datalayer.pojo.request.LoginRequest;
import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;
import com.example.ehab.japroject.datalayer.remote.service.LoginService;
import com.example.ehab.japroject.datalayer.remote.service.RegisterService;
import com.example.ehab.japroject.util.Constants;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;

import static com.example.ehab.japroject.datalayer.remote.ServiceError.NETWORK_ERROR;
import static com.example.ehab.japroject.datalayer.remote.ServiceError.SUCCESS_CODE;
import static com.example.ehab.japroject.util.Constants.BASE_URL;
import static com.example.ehab.japroject.util.Constants.ERROR_UNDEFINED;
import static com.example.ehab.japroject.util.NetworkUtils.isConnected;
import static java.util.Objects.isNull;

/**
 * Created by ehab on 12/2/17.
 */

public class RemoteRepository implements RemoteSource {

    private ServiceGenerator serviceGenerator;

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    @Override
    public Single checkUser(String email, String password) {
        Single<UserResponse> userResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LoginService loginService = serviceGenerator.createService(LoginService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(loginService.loginUser(new LoginRequest(email, password)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                UserResponse userResponse = (UserResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(userResponse);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return userResponseSingle;
    }

    @Override
    public Single registerUser(User user) {
        Single<UserResponse> userResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            RegisterService registerService = serviceGenerator.createService(RegisterService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(registerService.registerUser(user), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                UserResponse userResponse = (UserResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(userResponse);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return userResponseSingle;
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        if (!isConnected(JaApplication.getContext())) {
            return new ServiceResponse(new ServiceError());
        }
        try {
            retrofit2.Response response = call.execute();
            Gson gson = new Gson();
            if (response == null) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }

//    @Override
//    public Single registerUser(User user) {
//        RegisterService registerService  = serviceGenerator.createService(RegisterService.class,BASE_URL);
//        return registerService.registerUser(user);
//    }

//    @Override
//    public Single checkUser(String email, String password) {
//       LoginService loginService = serviceGenerator.createService(LoginService.class,BASE_URL);
//       return loginService.loginUser(new LoginRequest(email, password));
//    }
}
