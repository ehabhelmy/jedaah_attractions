package com.example.ehab.japroject.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.request.LoginRequest;
import com.example.ehab.japroject.datalayer.pojo.request.NearbyEventsRequest;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.remote.service.AllEventsService;
import com.example.ehab.japroject.datalayer.remote.service.CategoriesService;
import com.example.ehab.japroject.datalayer.remote.service.DataService;
import com.example.ehab.japroject.datalayer.remote.service.EventDetailsService;
import com.example.ehab.japroject.datalayer.remote.service.LikeService;
import com.example.ehab.japroject.datalayer.remote.service.LoginService;
import com.example.ehab.japroject.datalayer.remote.service.NearByEventsService;
import com.example.ehab.japroject.datalayer.remote.service.RegisterationService;
import com.example.ehab.japroject.datalayer.remote.service.SocialLoginService;
import com.example.ehab.japroject.datalayer.remote.service.TodayEventsService;
import com.example.ehab.japroject.datalayer.remote.service.TopEventsService;
import com.example.ehab.japroject.datalayer.remote.service.WeekEventsService;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.example.ehab.japroject.datalayer.remote.ServiceError.FALSE_CODE;
import static com.example.ehab.japroject.datalayer.remote.ServiceError.NETWORK_ERROR;
import static com.example.ehab.japroject.datalayer.remote.ServiceError.SUCCESS_CODE;
import static com.example.ehab.japroject.util.Constants.BASE_URL;
import static com.example.ehab.japroject.util.Constants.ERROR_UNDEFINED;
import static com.example.ehab.japroject.util.NetworkUtils.isConnected;

/**
 * Created by ehab on 12/2/17.
 */

public class RemoteRepository implements RemoteSource {

    private ServiceGenerator serviceGenerator;

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
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

    @Override
    public DataResponse getData() {
        DataService dataService = serviceGenerator.createService(DataService.class, BASE_URL);
        ServiceResponse serviceResponse = processCall(dataService.getData(getCurrentLanguage()), false);
        if (serviceResponse.getCode() == SUCCESS_CODE) {
            DataResponse dataResponse = (DataResponse) serviceResponse.getData();
            return dataResponse;
        } else {
            return null;
        }
    }

    @Override
    public Single<EventsResponse> getTopEvents() {
        Single<EventsResponse> topEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopEventsService topEventsService = serviceGenerator.createService(TopEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topEventsService.getTopEvents(getCurrentLanguage()), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventsResponse);
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
        return topEventsSingle;
    }

    @Override
    public Single<EventsResponse> getNearByEvents(LatLng latLng) {
        Single<EventsResponse> nearByEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearByEventsService nearByEventsService = serviceGenerator.createService(NearByEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearByEventsService.getNearbyEvents(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage()), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventsResponse);
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
        return nearByEventsSingle;
    }

    @Override
    public Single<Category> getCategories() {
        Single<Category> categoriesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            CategoriesService categoriesService = serviceGenerator.createService(CategoriesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(categoriesService.getCategories(getCurrentLanguage()), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                Category categoriesResponse = (Category) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(categoriesResponse);
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
        return categoriesResponseSingle;
    }

    @Override
    public Single<EventsResponse> getTodayEvents() {

        Single<EventsResponse> eventsResponseSingle = Single.create(singleOnSubscribe -> {
            if (!isConnected(JaApplication.getContext())) {
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            } else {
                try {
                    TodayEventsService todayEventsService = serviceGenerator.createService(TodayEventsService.class, BASE_URL);
                    ServiceResponse serviceResponse = processCall(todayEventsService.getTodayEvents(getCurrentLanguage()), false);
                    if (serviceResponse.getCode() == SUCCESS_CODE) {
                        EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                        singleOnSubscribe.onSuccess(eventsResponse);
                    } else {
                        Throwable throwable = new NetworkErrorException();
                        singleOnSubscribe.onError(throwable);
                    }
                } catch (Exception e) {
                    singleOnSubscribe.onError(e);
                }
            }
        });
        return eventsResponseSingle;
    }

    @Override
    public Single<EventsResponse> getWeekEvents() {
        Single<EventsResponse> eventsResponseSingle = Single.create(singleOnSubscribe -> {
            if (!isConnected(JaApplication.getContext())) {
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            } else {
                try {
                    WeekEventsService weekEventsService = serviceGenerator.createService(WeekEventsService.class, BASE_URL);
                    ServiceResponse serviceResponse = processCall(weekEventsService.getWeekEvents(getCurrentLanguage()), false);
                    if (serviceResponse.getCode() == SUCCESS_CODE) {
                        EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                        singleOnSubscribe.onSuccess(eventsResponse);
                    } else {
                        Throwable throwable = new NetworkErrorException();
                        singleOnSubscribe.onError(throwable);
                    }
                } catch (Exception e) {
                    singleOnSubscribe.onError(e);
                }
            }
        });
        return eventsResponseSingle;
    }

    @Override
    public Single<EventsResponse> getAllEvents() {
        Single<EventsResponse> allEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllEventsService allEventsService = serviceGenerator.createService(AllEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(allEventsService.getAllEvents(getCurrentLanguage()), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventsResponse);
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
        return allEventsSingle;
    }

    @Override
    public Single<EventInnerResponse> getEventDetails(int id) {
        Single<EventInnerResponse> eventInnerResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            EventDetailsService eventDetailsService = serviceGenerator.createService(EventDetailsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(eventDetailsService.getEventDetails(getCurrentLanguage(), id), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventInnerResponse eventInnerResponse = (EventInnerResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventInnerResponse);
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
        return eventInnerResponseSingle;
    }

    @Override
    public Single<LoginResponse> login(String email, String password) {
        Single<LoginResponse> loginResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LoginService loginService = serviceGenerator.createService(LoginService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(loginService.login(getCurrentLanguage(), new LoginRequest(email, password)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                LoginResponse loginResponse = (LoginResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(loginResponse);
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
        return loginResponseSingle;
    }

    @Override
    public Single<LoginResponse> sociaLogin(String facebookId,String googleId, String email) {
        Single<LoginResponse> loginResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SocialLoginService loginService = serviceGenerator.createService(SocialLoginService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(loginService.socialLogin(getCurrentLanguage(), new LoginRequest(email,facebookId,googleId)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                LoginResponse loginResponse = (LoginResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(loginResponse);
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
        return loginResponseSingle;
    }

    @Override
    public Single<RegisterationResponse> register(String userName, String email, String password, String mobile, File image) {
        Single<RegisterationResponse> registerationResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            serviceGenerator.setCONTENT_TYPE_VALUE("multipart/form-data");
                            RegisterationService registerationService = serviceGenerator.createService(RegisterationService.class, BASE_URL);
                            serviceGenerator.setCONTENT_TYPE_VALUE("application/json");
                            RequestBody requestName = RequestBody.create(MediaType.parse("text/plain"), "Ali");
                            RequestBody requestUserName = RequestBody.create(MediaType.parse("text/plain"), userName);
                            RequestBody requestEmail = RequestBody.create(MediaType.parse("text/plain"), email);
                            RequestBody requestPassword = RequestBody.create(MediaType.parse("text/plain"), password);
                            RequestBody requestMobile = null;
                            if (mobile != null) {
                                requestMobile = RequestBody.create(MediaType.parse("text/plain"), mobile);
                            }
                            MultipartBody.Part requestImagePart = null;
                            if (image != null) {
                                RequestBody requestImage = RequestBody.create(MediaType.parse("image/*"), image);
                                requestImagePart =
                                        MultipartBody.Part.createFormData("profile_image", image.getName(), requestImage);
                            }
                            ServiceResponse serviceResponse = processCall(registerationService.register(getCurrentLanguage(),
                                    requestName,
                                    requestUserName,
                                    requestEmail,
                                    requestPassword,
                                    requestMobile,
                                    requestImagePart), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE || serviceResponse.getCode() == FALSE_CODE) {
                                RegisterationResponse registerationResponse = (RegisterationResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(registerationResponse);
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
        return registerationResponseSingle;
    }

    @Override
    public Single<LikeResponse> like(int id, String token) {
        Single<LikeResponse> likeResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikeService likeService = serviceGenerator.createService(LikeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likeService.like(getCurrentLanguage(), id,token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                LikeResponse likeResponse = (LikeResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(likeResponse);
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
        return likeResponseSingle;
    }

    private String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
