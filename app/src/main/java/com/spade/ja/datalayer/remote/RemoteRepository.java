package com.spade.ja.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.spade.ja.JaApplication;
import com.spade.ja.datalayer.pojo.request.LoginRequest;
import com.spade.ja.datalayer.pojo.request.NearbyEventsRequest;
import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.request.changeorder.ChangeOrderRequest;
import com.spade.ja.datalayer.pojo.request.contactus.ContactUsRequest;
import com.spade.ja.datalayer.pojo.request.filter.filtercategories.FilterCategoriesRequest;
import com.spade.ja.datalayer.pojo.request.filter.filterevents.FilterEventsRequest;
import com.spade.ja.datalayer.pojo.request.filter.filtervenues.FilterVenuesRequest;
import com.spade.ja.datalayer.pojo.request.order.OrderRequest;
import com.spade.ja.datalayer.pojo.request.resetpassword.ResetCodeRequest;
import com.spade.ja.datalayer.pojo.request.resetpassword.ResetPasswordRequest;
import com.spade.ja.datalayer.pojo.request.search.SearchRequest;
import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.about.AboutUsResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.attractionconfirm.AttractionConfirmOrderResponse;
import com.spade.ja.datalayer.pojo.response.attractionhistory.AttractionOrderHistoryResponse;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.code.ResetCodeResponse;
import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.datalayer.pojo.response.eventcreditconfirm.EventChangeStatusResponse;
import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;
import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;
import com.spade.ja.datalayer.pojo.response.subscribe.SubscribeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
import com.spade.ja.datalayer.pojo.response.viewtickets.ViewTicketResponse;
import com.spade.ja.datalayer.remote.service.AboutUsService;
import com.spade.ja.datalayer.remote.service.AllAttractionsService;
import com.spade.ja.datalayer.remote.service.AllEventsService;
import com.spade.ja.datalayer.remote.service.AllNearyByService;
import com.spade.ja.datalayer.remote.service.AllVenuesService;
import com.spade.ja.datalayer.remote.service.AttractionCreditOrderChange;
import com.spade.ja.datalayer.remote.service.AttractionInnerService;
import com.spade.ja.datalayer.remote.service.AttractionOrderPastService;
import com.spade.ja.datalayer.remote.service.AttractionOrderService;
import com.spade.ja.datalayer.remote.service.AttractionOrderUpcomingService;
import com.spade.ja.datalayer.remote.service.AttractionsLikeService;
import com.spade.ja.datalayer.remote.service.CancelAttractionService;
import com.spade.ja.datalayer.remote.service.CancelEventService;
import com.spade.ja.datalayer.remote.service.CategoriesService;
import com.spade.ja.datalayer.remote.service.ContactUsDataService;
import com.spade.ja.datalayer.remote.service.ContactUsService;
import com.spade.ja.datalayer.remote.service.DataService;
import com.spade.ja.datalayer.remote.service.DirectoryCategoriesListService;
import com.spade.ja.datalayer.remote.service.EditService;
import com.spade.ja.datalayer.remote.service.EventCreditOrderChange;
import com.spade.ja.datalayer.remote.service.EventDetailsService;
import com.spade.ja.datalayer.remote.service.ExploreCategoriesListService;
import com.spade.ja.datalayer.remote.service.FilterAttractionService;
import com.spade.ja.datalayer.remote.service.FilterEventsService;
import com.spade.ja.datalayer.remote.service.FilterVenuesService;
import com.spade.ja.datalayer.remote.service.LikeService;
import com.spade.ja.datalayer.remote.service.LikedAttractionsService;
import com.spade.ja.datalayer.remote.service.LikedEventsService;
import com.spade.ja.datalayer.remote.service.LikedVenuesService;
import com.spade.ja.datalayer.remote.service.LoginService;
import com.spade.ja.datalayer.remote.service.NearByAttractions;
import com.spade.ja.datalayer.remote.service.NearByEventsService;
import com.spade.ja.datalayer.remote.service.NearyByVenuesService;
import com.spade.ja.datalayer.remote.service.OrderService;
import com.spade.ja.datalayer.remote.service.PastEventsService;
import com.spade.ja.datalayer.remote.service.ProfileService;
import com.spade.ja.datalayer.remote.service.RegisterationService;
import com.spade.ja.datalayer.remote.service.ResetCodeService;
import com.spade.ja.datalayer.remote.service.ResetPasswordService;
import com.spade.ja.datalayer.remote.service.SMSService;
import com.spade.ja.datalayer.remote.service.SearchService;
import com.spade.ja.datalayer.remote.service.SocialLoginService;
import com.spade.ja.datalayer.remote.service.SubscribeService;
import com.spade.ja.datalayer.remote.service.TodayEventsService;
import com.spade.ja.datalayer.remote.service.TopAttractionsService;
import com.spade.ja.datalayer.remote.service.TopEventsService;
import com.spade.ja.datalayer.remote.service.TopVenuesService;
import com.spade.ja.datalayer.remote.service.UpcomingEventsService;
import com.spade.ja.datalayer.remote.service.VenueDetailsService;
import com.spade.ja.datalayer.remote.service.VenuesLikeService;
import com.spade.ja.datalayer.remote.service.ViewTicketsService;
import com.spade.ja.datalayer.remote.service.WeekEventsService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.spade.ja.datalayer.remote.ServiceError.FALSE_CODE;
import static com.spade.ja.datalayer.remote.ServiceError.INVALID_CODE;
import static com.spade.ja.datalayer.remote.ServiceError.NETWORK_ERROR;
import static com.spade.ja.datalayer.remote.ServiceError.SUCCESS_CODE;
import static com.spade.ja.util.Constants.BASE_URL;
import static com.spade.ja.util.Constants.ERROR_UNDEFINED;
import static com.spade.ja.util.Constants.JAWAL_URL;
import static com.spade.ja.util.Constants.PASSWORD;
import static com.spade.ja.util.Constants.SENDER;
import static com.spade.ja.util.Constants.USER_NAME;
import static com.spade.ja.util.NetworkUtils.isConnected;

/**
 * Created by ehab on 12/2/17.
 */

public class RemoteRepository implements RemoteSource {

    private ServiceGenerator serviceGenerator;
    public static final String BEARER = "bearer ";

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
                return new ServiceResponse(responseCode, isVoid ? false : response.body());
            } else {
                if (responseCode == 406 || responseCode == 401) {
                    List<String> urlPaths = response.raw().request().url().encodedPathSegments();
                    if (!urlPaths.get(urlPaths.size() - 1).equals("order")) {
                        return new ServiceResponse(responseCode, isVoid ? null : gson.fromJson(response.errorBody().string(), LoginResponse.class));
                    } else {
                        if (urlPaths.get(urlPaths.size() - 3).contains("attraction")) {
                            return new ServiceResponse(responseCode, isVoid ? null : gson.fromJson(response.errorBody().string(), AttractionOrderResponse.class));
                        } else {
                            return new ServiceResponse(responseCode, isVoid ? null : gson.fromJson(response.errorBody().string(), OrderResponse.class));
                        }
                    }
                }
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.errorBody().string(), responseCode);
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
            return (DataResponse) serviceResponse.getData();
        } else {
            return null;
        }
    }

    @Override
    public Single<EventsResponse> getTopEvents(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopEventsService topEventsService = serviceGenerator.createService(TopEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topEventsService.getTopEvents(getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<EventsResponse> getNearByEvents(LatLng latLng, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearByEventsService nearByEventsService = serviceGenerator.createService(NearByEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearByEventsService.getNearbyEvents(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<VenuesResponse> getTopVenues(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopVenuesService topVenuesService = serviceGenerator.createService(TopVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topVenuesService.getTopVenues(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesResponse venuesResponse = (VenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesResponse);
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
    }

    @Override
    public Single<VenuesResponse> getTopAttractions(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopAttractionsService topAttractionsService = serviceGenerator.createService(TopAttractionsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topAttractionsService.getTopAttractions(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesResponse venuesResponse = (VenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesResponse);
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
    }

    @Override
    public Single<AllVenuesResponse> getAllVenues(String venueURL, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllVenuesService allVenuesService = serviceGenerator.createService(AllVenuesService.class, BASE_URL);
                            char page = venueURL == null ? '1' : venueURL.charAt(venueURL.length() - 1);
                            ServiceResponse serviceResponse = processCall(allVenuesService.getAllVenues(getCurrentLanguage(), Character.getNumericValue(page), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllVenuesResponse venuesResponse = (AllVenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesResponse);
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
    }

    @Override
    public Single<AllVenuesResponse> getAllAttractions(String venueURL, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllAttractionsService allVenuesService = serviceGenerator.createService(AllAttractionsService.class, BASE_URL);
                            char page = venueURL == null ? '1' : venueURL.charAt(venueURL.length() - 1);
                            ServiceResponse serviceResponse = processCall(allVenuesService.getAllAttractions(getCurrentLanguage(), Character.getNumericValue(page), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllVenuesResponse venuesResponse = (AllVenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesResponse);
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
    }

    @Override
    public Single<VenuesResponse> getNearByVenues(LatLng latLng, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearyByVenuesService nearyByVenuesService = serviceGenerator.createService(NearyByVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearyByVenuesService.getNearbyVenues(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesResponse venuesResponse = (VenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesResponse);
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
    }

    @Override
    public Single<VenuesResponse> getNearByAttractions(LatLng latLng, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearByAttractions nearByAttractions = serviceGenerator.createService(NearByAttractions.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearByAttractions.getNearbyAttractions(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesResponse venuesResponse = (VenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesResponse);
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
    }

    @Override
    public Single<AllNearByResponse> getNearByAll(LatLng latLng, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllNearyByService allNearyByService = serviceGenerator.createService(AllNearyByService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(allNearyByService.getNearByAll(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllNearByResponse allNearByResponse = (AllNearByResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(allNearByResponse);
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
    }

    @Override
    public Single<Category> getCategories() {
        return Single.create(singleOnSubscribe -> {
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
    }

    @Override
    public Single<EventsResponse> getTodayEvents(String token) {

        return Single.create(singleOnSubscribe -> {
            if (!isConnected(JaApplication.getContext())) {
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            } else {
                try {
                    TodayEventsService todayEventsService = serviceGenerator.createService(TodayEventsService.class, BASE_URL);
                    ServiceResponse serviceResponse = processCall(todayEventsService.getTodayEvents(getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<EventsResponse> getWeekEvents(String token) {
        return Single.create(singleOnSubscribe -> {
            if (!isConnected(JaApplication.getContext())) {
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            } else {
                try {
                    WeekEventsService weekEventsService = serviceGenerator.createService(WeekEventsService.class, BASE_URL);
                    ServiceResponse serviceResponse = processCall(weekEventsService.getWeekEvents(getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<AllEventsResponse> getAllEvents(String newURL, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllEventsService allEventsService = serviceGenerator.createService(AllEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = null;
                            char page = newURL.charAt(newURL.length() - 1);
                            serviceResponse = processCall(allEventsService.getAllEvents(getCurrentLanguage(), Character.getNumericValue(page), BEARER + token), false);
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
    }

    @Override
    public Single<EventsResponse> getLikedEvents(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikedEventsService likedEventsService = serviceGenerator.createService(LikedEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likedEventsService.getLikedEvents(getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<VenuesResponse> getLikedVenues(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikedVenuesService likedEventsService = serviceGenerator.createService(LikedVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likedEventsService.getLikedVenues(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesResponse eventsResponse = (VenuesResponse) serviceResponse.getData();
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
    }

    @Override
    public Single<VenuesResponse> getLikedAttractions(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikedAttractionsService likedEventsService = serviceGenerator.createService(LikedAttractionsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likedEventsService.getLikedAttractions(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesResponse eventsResponse = (VenuesResponse) serviceResponse.getData();
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
    }

    @Override
    public Single<EventInnerResponse> getEventDetails(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            EventDetailsService eventDetailsService = serviceGenerator.createService(EventDetailsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(eventDetailsService.getEventDetails(getCurrentLanguage(), id, BEARER + token), false);
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
    }

    @Override
    public Single<VenuesInnerResponse> getVenueDetails(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            VenueDetailsService venueDetailsService = serviceGenerator.createService(VenueDetailsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(venueDetailsService.getVenueDetails(getCurrentLanguage(), id, BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                VenuesInnerResponse venuesInnerResponse = (VenuesInnerResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesInnerResponse);
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
    }

    @Override
    public Single<AttractionInnerResponse> getAttractionsDetails(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionInnerService attractionInnerService = serviceGenerator.createService(AttractionInnerService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(attractionInnerService.getAttractionsDetails(getCurrentLanguage(), id, BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AttractionInnerResponse venuesInnerResponse = (AttractionInnerResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(venuesInnerResponse);
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
    }

    @Override
    public Single<ViewTicketResponse> viewAttractionTickets(String startTime, String endTime, String date, String token, int id) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ViewTicketsService viewTicketsService = serviceGenerator.createService(ViewTicketsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(viewTicketsService.viewTickets(getCurrentLanguage(), startTime, endTime, date, id, BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ViewTicketResponse viewTicketResponse = (ViewTicketResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(viewTicketResponse);
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
    }

    @Override
    public Single<LoginResponse> login(String email, String password) {
        return Single.create(singleOnSubscribe -> {
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
    }

    @Override
    public Single<LoginResponse> sociaLogin(String facebookId, String googleId, String email) {
        return Single.create(singleOnSubscribe -> {
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
    }

    @Override
    public Single<LoginResponse> register(String userName, String email, String password, String mobile, File image) {
        return Single.create(singleOnSubscribe -> {
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
    }

    @Override
    public Single<SubscribeResponse> subscribe(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SubscribeService subscribeService = serviceGenerator.createService(SubscribeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(subscribeService.subscribe(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                SubscribeResponse likeResponse = (SubscribeResponse) serviceResponse.getData();
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
    }

    @Override
    public Single<LoginResponse> edit(String token, String userName, String email, String dateOfBirth, String gender, String password, String mobile, File image) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            serviceGenerator.setCONTENT_TYPE_VALUE("multipart/form-data");
                            EditService editService = serviceGenerator.createService(EditService.class, BASE_URL);
                            serviceGenerator.setCONTENT_TYPE_VALUE("application/json");
                            RequestBody requestName = null;
                            RequestBody requestEmail = null;
                            RequestBody requestPassword = null;
                            RequestBody requestMobile = null;
                            RequestBody requestDateOfBirth = null;
                            RequestBody requestGender = null;
                            if (!TextUtils.isEmpty(userName)) {
                                requestName = RequestBody.create(MediaType.parse("text/plain"), userName);
                            }
                            if (!TextUtils.isEmpty(email)) {
                                requestEmail = RequestBody.create(MediaType.parse("text/plain"), email);
                            }
                            if (!TextUtils.isEmpty(password)) {
                                requestPassword = RequestBody.create(MediaType.parse("text/plain"), password);
                            }
                            if (!TextUtils.isEmpty(mobile)) {
                                requestMobile = RequestBody.create(MediaType.parse("text/plain"), mobile);
                            }
                            if (!TextUtils.isEmpty(dateOfBirth)) {
                                requestDateOfBirth = RequestBody.create(MediaType.parse("text/plain"), dateOfBirth);
                            }
                            if (!TextUtils.isEmpty(gender)) {
                                requestGender = RequestBody.create(MediaType.parse("text/plain"), gender);
                            }
                            MultipartBody.Part requestImagePart = null;
                            if (image != null) {
                                RequestBody requestImage = RequestBody.create(MediaType.parse("image/*"), image);
                                requestImagePart =
                                        MultipartBody.Part.createFormData("profile_image", image.getName(), requestImage);
                            }
                            ServiceResponse serviceResponse = processCall(editService.edit(getCurrentLanguage(),
                                    "bearer " + token,
                                    requestName,
                                    requestEmail,
                                    requestPassword,
                                    requestMobile,
                                    requestDateOfBirth,
                                    requestGender,
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
    }

    @Override
    public Single<LikeResponse> like(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikeService likeService = serviceGenerator.createService(LikeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likeService.like(getCurrentLanguage(), id, BEARER + token), false);
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
    }

    @Override
    public Single<LikeResponse> likeVenues(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            VenuesLikeService likeService = serviceGenerator.createService(VenuesLikeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likeService.like(getCurrentLanguage(), id, BEARER + token), false);
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
    }

    @Override
    public Single<LikeResponse> likeAttractions(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionsLikeService likeService = serviceGenerator.createService(AttractionsLikeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likeService.like(getCurrentLanguage(), id, BEARER + token), false);
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
    }

    @Override
    public Single<ProfileResponse> getProfile(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ProfileService profileService = serviceGenerator.createService(ProfileService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(profileService.getProfile(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ProfileResponse profileResponse = (ProfileResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(profileResponse);
                            } else {
                                Throwable throwable = new Exception();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
    }

    @Override
    public Single<OrderResponse> order(String token, String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            OrderService orderService = serviceGenerator.createServiceNotNullSerialization(OrderService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(orderService.order(getCurrentLanguage(), BEARER + token, new OrderRequest(name, email, mobileNumber, numberOfTickets, paymentMethod, eventId, ticketId, dateId, nationalId, total)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE || serviceResponse.getCode() == FALSE_CODE) {
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
    }

    @Override
    public Single<HistoryEvents> getUpcomingEvents(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            UpcomingEventsService upcomingEventsService = serviceGenerator.createService(UpcomingEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(upcomingEventsService.getUpcomingEvents(getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<HistoryEvents> getPastEvents(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            PastEventsService pastEventsService = serviceGenerator.createService(PastEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(pastEventsService.getPastEvents(getCurrentLanguage(), BEARER + token), false);
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
    }

    @Override
    public Single<AttractionOrderHistoryResponse> getUpcomingAttractions(String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionOrderUpcomingService attractionOrderUpcomingService = serviceGenerator.createService(AttractionOrderUpcomingService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(attractionOrderUpcomingService.getUpcomingAttractions(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AttractionOrderHistoryResponse historyEvents = (AttractionOrderHistoryResponse) serviceResponse.getData();
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
    }

    @Override
    public Single<AttractionOrderHistoryResponse> getPastAttractions(String token) {
        return Single.create((SingleEmitter<AttractionOrderHistoryResponse> singleOnSubscribe) -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionOrderPastService attractionOrderUpcomingService = serviceGenerator.createService(AttractionOrderPastService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(attractionOrderUpcomingService.getPastAttractions(getCurrentLanguage(), BEARER + token), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AttractionOrderHistoryResponse historyEvents = (AttractionOrderHistoryResponse) serviceResponse.getData();
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
    }

    @Override
    public Single<SMSResponse> sendSMS(String phone) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SMSService smsService = serviceGenerator.createService(SMSService.class, JAWAL_URL);
                            String msg = generatePIN();
                            ServiceResponse serviceResponse = processCall(smsService.sendSMS(USER_NAME, PASSWORD, phone, msg, SENDER), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                SMSResponse smsResponse = (SMSResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(smsResponse);
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
    }

    @Override
    public Single<FilterEventsResponse> filterEvents(boolean weeklySuggest, List<Integer> categoryId, int date, Double lat, Double lng) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            FilterEventsService filterEventsService = serviceGenerator.createServiceNotNullSerialization(FilterEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(filterEventsService.filterEvents(getCurrentLanguage(), date != 0 ? new FilterEventsRequest(weeklySuggest, categoryId, date, lat, lng) : new FilterEventsRequest(weeklySuggest, categoryId, null, lat, lng)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                FilterEventsResponse filterEventsResponse = (FilterEventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(filterEventsResponse);
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
    }

    @Override
    public Single<FilterVenuesResponse> filterVenues(boolean weeklySuggest, List<Integer> categoryId, Double lat, Double lng) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            FilterVenuesService filterVenuesService = serviceGenerator.createServiceNotNullSerialization(FilterVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(filterVenuesService.filterVenues(getCurrentLanguage(), new FilterVenuesRequest(weeklySuggest, categoryId, lat, lng)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                FilterVenuesResponse filterVenuesResponse = (FilterVenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(filterVenuesResponse);
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
    }

    @Override
    public Single<FilterVenuesResponse> filterAttracions(boolean weeklySuggest, List<Integer> categoryId, Double lat, Double lng) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            FilterAttractionService filterVenuesService = serviceGenerator.createServiceNotNullSerialization(FilterAttractionService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(filterVenuesService.filterAttractions(getCurrentLanguage(), new FilterVenuesRequest(weeklySuggest, categoryId, lat, lng)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                FilterVenuesResponse filterVenuesResponse = (FilterVenuesResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(filterVenuesResponse);
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
    }

    @Override
    public Single<AllNearByResponse> filterCatsExplore(List<Integer> categoryId) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ExploreCategoriesListService service = serviceGenerator.createServiceNotNullSerialization(ExploreCategoriesListService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(service.filterEvents(getCurrentLanguage(), new FilterCategoriesRequest(categoryId)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllNearByResponse filterVenuesResponse = (AllNearByResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(filterVenuesResponse);
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
    }

    @Override
    public Single<AllNearByResponse> filterCatsDirectories(List<Integer> categoryId) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            DirectoryCategoriesListService service = serviceGenerator.createServiceNotNullSerialization(DirectoryCategoriesListService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(service.filterEvents(getCurrentLanguage(), new FilterCategoriesRequest(categoryId)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllNearByResponse filterVenuesResponse = (AllNearByResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(filterVenuesResponse);
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
    }

    @Override
    public Single<AllNearByResponse> search(String searchWord, List<String> types, List<Integer> categoryId) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SearchService searchService = serviceGenerator.createService(SearchService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(searchService.search(getCurrentLanguage(), new SearchRequest(searchWord, types, categoryId)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AllNearByResponse data = (AllNearByResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(data);
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
    }

    @Override
    public Single<ContactUsResponse> contactUs(String subject, String message, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ContactUsService contactUsService = serviceGenerator.createService(ContactUsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsService.contactUs(getCurrentLanguage(), BEARER + token, new ContactUsRequest(subject, message)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ContactUsResponse contactUsResponse = (ContactUsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }

    @Override
    public Single<AttractionOrderResponse> orderAttraction(AttractionOrderRequest attractionOrderRequest, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionOrderService attractionOrderService = serviceGenerator.createService(AttractionOrderService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(attractionOrderService.attractionOrder(getCurrentLanguage(), BEARER + token, attractionOrderRequest), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE || serviceResponse.getCode() == FALSE_CODE) {
                                AttractionOrderResponse attractionOrderResponse = (AttractionOrderResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(attractionOrderResponse);
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
    }

    @Override
    public Single<ResetCodeResponse> getCode(String email) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ResetCodeService contactUsService = serviceGenerator.createService(ResetCodeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsService.getCode(getCurrentLanguage(), new ResetCodeRequest(email)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ResetCodeResponse contactUsResponse = (ResetCodeResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }

    @Override
    public Single<ContactUsResponse> resetPassword(String password, String code) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ResetPasswordService contactUsService = serviceGenerator.createService(ResetPasswordService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsService.resetPassword(getCurrentLanguage(), new ResetPasswordRequest(code, password)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ContactUsResponse contactUsResponse = (ContactUsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }

    @Override
    public Single<AboutUsResponse> about() {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AboutUsService aboutUsService = serviceGenerator.createService(AboutUsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(aboutUsService.about(getCurrentLanguage()), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AboutUsResponse contactUsResponse = (AboutUsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }

    @Override
    public Single<ContactUsResponse> cancelAttraction(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            CancelAttractionService contactUsService = serviceGenerator.createService(CancelAttractionService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsService.cancelAttraction(getCurrentLanguage(), BEARER + token, id), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ContactUsResponse contactUsResponse = (ContactUsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }

    @Override
    public Single<ContactUsResponse> cancelEvent(int id, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            CancelEventService contactUsService = serviceGenerator.createService(CancelEventService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsService.cancelEvent(getCurrentLanguage(), BEARER + token, id), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ContactUsResponse contactUsResponse = (ContactUsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }

    @Override
    public Single<EventChangeStatusResponse> changeOrderCreditEvent(String orderId, String status, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            EventCreditOrderChange service = serviceGenerator.createService(EventCreditOrderChange.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(service.changeOrder(getCurrentLanguage(), BEARER + token, new ChangeOrderRequest(orderId, status)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventChangeStatusResponse model = (EventChangeStatusResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(model);
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
    }

    @Override
    public Single<AttractionConfirmOrderResponse> changeOrderCreditAttraction(String orderId, String status, String token) {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionCreditOrderChange service = serviceGenerator.createService(AttractionCreditOrderChange.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(service.changeOrder(getCurrentLanguage(), BEARER + token, new ChangeOrderRequest(orderId, status)), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                AttractionConfirmOrderResponse model = (AttractionConfirmOrderResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(model);
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
    }

    @Override
    public Single<ContactUsDataResponse> getContactUsData() {
        return Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ContactUsDataService contactUsDataService = serviceGenerator.createService(ContactUsDataService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsDataService.contactUs(getCurrentLanguage()), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                ContactUsDataResponse contactUsResponse = (ContactUsDataResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(contactUsResponse);
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
    }


    private String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    private String generatePIN() {

        //generate a 4 digit integer 1000 <10000
        int randomPIN = (new Random().nextInt() * 9000) + 1000;

        //Store integer in a string
        return String.valueOf(randomPIN);
    }
}
