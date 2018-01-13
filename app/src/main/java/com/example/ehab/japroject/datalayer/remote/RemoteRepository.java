package com.example.ehab.japroject.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.request.LoginRequest;
import com.example.ehab.japroject.datalayer.pojo.request.NearbyEventsRequest;
import com.example.ehab.japroject.datalayer.pojo.request.order.OrderRequest;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.allevents.AllEventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.pojo.response.order.OrderResponse;
import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;
import com.example.ehab.japroject.datalayer.remote.service.AllEventsService;
import com.example.ehab.japroject.datalayer.remote.service.CategoriesService;
import com.example.ehab.japroject.datalayer.remote.service.DataService;
import com.example.ehab.japroject.datalayer.remote.service.EventDetailsService;
import com.example.ehab.japroject.datalayer.remote.service.LikeService;
import com.example.ehab.japroject.datalayer.remote.service.LikedEventsService;
import com.example.ehab.japroject.datalayer.remote.service.LoginService;
import com.example.ehab.japroject.datalayer.remote.service.NearByEventsService;
import com.example.ehab.japroject.datalayer.remote.service.OrderService;
import com.example.ehab.japroject.datalayer.remote.service.PastEventsService;
import com.example.ehab.japroject.datalayer.remote.service.ProfileService;
import com.example.ehab.japroject.datalayer.remote.service.RegisterationService;
import com.example.ehab.japroject.datalayer.remote.service.SocialLoginService;
import com.example.ehab.japroject.datalayer.remote.service.TodayEventsService;
import com.example.ehab.japroject.datalayer.remote.service.TopEventsService;
import com.example.ehab.japroject.datalayer.remote.service.UpcomingEventsService;
import com.example.ehab.japroject.datalayer.remote.service.WeekEventsService;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.example.ehab.japroject.datalayer.remote.ServiceError.ERROR_CODE;
import static com.example.ehab.japroject.datalayer.remote.ServiceError.FALSE_CODE;
import static com.example.ehab.japroject.datalayer.remote.ServiceError.INVALID_CODE;
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
                if (responseCode == 406 || responseCode == 401){
                    return new ServiceResponse(responseCode,isVoid ? null : gson.fromJson(response.errorBody().string(),LoginResponse.class));
                }
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
    public Single<EventsResponse> getTopEvents(String token) {
        Single<EventsResponse> topEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopEventsService topEventsService = serviceGenerator.createService(TopEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topEventsService.getTopEvents(getCurrentLanguage(),token), false);
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
    public Single<EventsResponse> getNearByEvents(LatLng latLng,String token) {
        Single<EventsResponse> nearByEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearByEventsService nearByEventsService = serviceGenerator.createService(NearByEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearByEventsService.getNearbyEvents(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(),token), false);
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
    public Single<EventsResponse> getTodayEvents(String token) {

        Single<EventsResponse> eventsResponseSingle = Single.create(singleOnSubscribe -> {
            if (!isConnected(JaApplication.getContext())) {
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            } else {
                try {
                    TodayEventsService todayEventsService = serviceGenerator.createService(TodayEventsService.class, BASE_URL);
                    ServiceResponse serviceResponse = processCall(todayEventsService.getTodayEvents(getCurrentLanguage(),token), false);
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
    public Single<EventsResponse> getWeekEvents(String token) {
        Single<EventsResponse> eventsResponseSingle = Single.create(singleOnSubscribe -> {
            if (!isConnected(JaApplication.getContext())) {
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            } else {
                try {
                    WeekEventsService weekEventsService = serviceGenerator.createService(WeekEventsService.class, BASE_URL);
                    ServiceResponse serviceResponse = processCall(weekEventsService.getWeekEvents(getCurrentLanguage(),token), false);
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
    public Single<AllEventsResponse> getAllEvents(String newURL,String token) {
        Single<AllEventsResponse> allEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllEventsService allEventsService = serviceGenerator.createService(AllEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = null;
                            char page = newURL.charAt(newURL.length() - 1);
                            serviceResponse = processCall(allEventsService.getAllEvents(getCurrentLanguage(),Character.getNumericValue(page),token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllEventsResponse eventsResponse = (AllEventsResponse) serviceResponse.getData();
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
    public Single<EventsResponse> getLikedEvents(String token) {
        Single<EventsResponse> likedEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikedEventsService likedEventsService = serviceGenerator.createService(LikedEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likedEventsService.getLikedEvents(getCurrentLanguage(),"bearer "+token), false);
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
        return likedEventsSingle;
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
                            if (serviceResponse.getCode() == SUCCESS_CODE || serviceResponse.getCode() == FALSE_CODE || serviceResponse.getCode() == INVALID_CODE) {
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
    public Single<LoginResponse> sociaLogin(String facebookId, String googleId, String email) {
        Single<LoginResponse> loginResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SocialLoginService loginService = serviceGenerator.createService(SocialLoginService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(loginService.socialLogin(getCurrentLanguage(), new LoginRequest(email, facebookId, googleId)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE || serviceResponse.getCode() == FALSE_CODE || serviceResponse.getCode() == INVALID_CODE) {
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
    public Single<LoginResponse> register(String userName, String email, String password, String mobile, File image) {
        Single<LoginResponse> registerationResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            serviceGenerator.setCONTENT_TYPE_VALUE("multipart/form-data");
                            RegisterationService registerationService = serviceGenerator.createService(RegisterationService.class, BASE_URL);
                            serviceGenerator.setCONTENT_TYPE_VALUE("application/json");
                            RequestBody requestName = RequestBody.create(MediaType.parse("text/plain"), userName);
                            RequestBody requestUserName = RequestBody.create(MediaType.parse("text/plain"), userName);
                            RequestBody requestEmail = RequestBody.create(MediaType.parse("text/plain"), email);
                            RequestBody requestPassword = RequestBody.create(MediaType.parse("text/plain"), password);
                            RequestBody requestMobile = null;
                            if (!TextUtils.isEmpty(mobile)) {
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
                            ServiceResponse serviceResponse = processCall(likeService.like(getCurrentLanguage(), id,"bearer "+token), false);
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

    @Override
    public Single<ProfileResponse> getProfile(String token) {
        Single<ProfileResponse> profileResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ProfileService profileService = serviceGenerator.createService(ProfileService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(profileService.getProfile(getCurrentLanguage(), "bearer " + token ), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ProfileResponse profileResponse = (ProfileResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(profileResponse);
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
        return profileResponseSingle;
    }

    @Override
    public Single<OrderResponse> order(String token,String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total) {
        Single<OrderResponse> orderResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            OrderService orderService = serviceGenerator.createService(OrderService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(orderService.order(getCurrentLanguage(), "bearer " + token,new OrderRequest(name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE || serviceResponse.getCode() == ERROR_CODE) {
                                OrderResponse orderResponse = (OrderResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(orderResponse);
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
        return orderResponseSingle;
    }

    @Override
    public Single<HistoryEvents> getUpcomingEvents(String token) {
        Single<HistoryEvents> historyEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            UpcomingEventsService upcomingEventsService = serviceGenerator.createService(UpcomingEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(upcomingEventsService.getUpcomingEvents(getCurrentLanguage(),"bearer "+token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                HistoryEvents historyEvents = (HistoryEvents) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(historyEvents);
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
        return historyEventsSingle;
    }

    @Override
    public Single<HistoryEvents> getPastEvents(String token) {
        Single<HistoryEvents> historyEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            PastEventsService pastEventsService = serviceGenerator.createService(PastEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(pastEventsService.getPastEvents(getCurrentLanguage(),"bearer "+token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                HistoryEvents historyEvents = (HistoryEvents) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(historyEvents);
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
        return historyEventsSingle;
    }

    private String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
