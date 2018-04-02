package com.spade.ja.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.spade.ja.JaApplication;
import com.spade.ja.datalayer.pojo.request.LoginRequest;
import com.spade.ja.datalayer.pojo.request.NearbyEventsRequest;
import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.request.contactus.ContactUsRequest;
import com.spade.ja.datalayer.pojo.request.filter.filterevents.FilterEventsRequest;
import com.spade.ja.datalayer.pojo.request.filter.filtervenues.FilterVenuesRequest;
import com.spade.ja.datalayer.pojo.request.order.OrderRequest;
import com.spade.ja.datalayer.pojo.request.search.SearchRequest;
import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
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
import com.spade.ja.datalayer.remote.service.AllAttractionsService;
import com.spade.ja.datalayer.remote.service.AllEventsService;
import com.spade.ja.datalayer.remote.service.AllNearyByService;
import com.spade.ja.datalayer.remote.service.AllVenuesService;
import com.spade.ja.datalayer.remote.service.AttractionInnerService;
import com.spade.ja.datalayer.remote.service.AttractionOrderService;
import com.spade.ja.datalayer.remote.service.AttractionsLikeService;
import com.spade.ja.datalayer.remote.service.CategoriesService;
import com.spade.ja.datalayer.remote.service.ContactUsService;
import com.spade.ja.datalayer.remote.service.DataService;
import com.spade.ja.datalayer.remote.service.EditService;
import com.spade.ja.datalayer.remote.service.EventDetailsService;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
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
                    List<String> urlPaths = response.raw().request().url().encodedPathSegments();
                    if (!urlPaths.get(urlPaths.size() -1).equals("order")) {
                        return new ServiceResponse(responseCode, isVoid ? null : gson.fromJson(response.errorBody().string(), LoginResponse.class));
                    }else {
                        return new ServiceResponse(responseCode, isVoid ? null : gson.fromJson(response.errorBody().string(), OrderResponse.class));
                    }
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
    public Single<VenuesResponse> getTopVenues(String token) {
        Single<VenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopVenuesService topVenuesService = serviceGenerator.createService(TopVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topVenuesService.getTopVenues(getCurrentLanguage(),token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<VenuesResponse> getTopAttractions(String token) {
        Single<VenuesResponse> attractionsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopAttractionsService topAttractionsService = serviceGenerator.createService(TopAttractionsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topAttractionsService.getTopAttractions(getCurrentLanguage(),token), false);
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
        return attractionsSingle;
    }

    @Override
    public Single<AllVenuesResponse> getAllVenues(String venueURL, String token) {
        Single<AllVenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllVenuesService allVenuesService = serviceGenerator.createService(AllVenuesService.class, BASE_URL);
                            char page = venueURL == null ? '1':venueURL.charAt(venueURL.length() - 1);
                            ServiceResponse serviceResponse = processCall(allVenuesService.getAllVenues(getCurrentLanguage(),Character.getNumericValue(page),token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<AllVenuesResponse> getAllAttractions(String venueURL, String token) {
        Single<AllVenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllAttractionsService allVenuesService = serviceGenerator.createService(AllAttractionsService.class, BASE_URL);
                            char page = venueURL == null ? '1':venueURL.charAt(venueURL.length() - 1);
                            ServiceResponse serviceResponse = processCall(allVenuesService.getAllAttractions(getCurrentLanguage(),Character.getNumericValue(page),token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<VenuesResponse> getNearByVenues(LatLng latLng, String token) {
        Single<VenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearyByVenuesService nearyByVenuesService = serviceGenerator.createService(NearyByVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearyByVenuesService.getNearbyVenues(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(),token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<VenuesResponse> getNearByAttractions(LatLng latLng, String token) {
        Single<VenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearByAttractions nearByAttractions = serviceGenerator.createService(NearByAttractions.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearByAttractions.getNearbyAttractions(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(),token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<AllNearByResponse> getNearByAll(LatLng latLng, String token) {
        Single<AllNearByResponse> allNearByResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllNearyByService allNearyByService = serviceGenerator.createService(AllNearyByService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(allNearyByService.getNearByAll(new NearbyEventsRequest(latLng.latitude + "", latLng.longitude + ""), getCurrentLanguage(),token), false);
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
        return allNearByResponseSingle;
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
    public Single<VenuesResponse> getLikedVenues(String token) {
        Single<VenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikedVenuesService likedEventsService = serviceGenerator.createService(LikedVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likedEventsService.getLikedVenues(getCurrentLanguage(),"bearer "+token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<VenuesResponse> getLikedAttractions(String token) {
        Single<VenuesResponse> venuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LikedAttractionsService likedEventsService = serviceGenerator.createService(LikedAttractionsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(likedEventsService.getLikedAttractions(getCurrentLanguage(),"bearer "+token), false);
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
        return venuesResponseSingle;
    }

    @Override
    public Single<EventInnerResponse> getEventDetails(int id,String token) {
        Single<EventInnerResponse> eventInnerResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            EventDetailsService eventDetailsService = serviceGenerator.createService(EventDetailsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(eventDetailsService.getEventDetails(getCurrentLanguage(), id,"bearer "+token), false);
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
    public Single<VenuesInnerResponse> getVenueDetails(int id,String token) {
        Single<VenuesInnerResponse> venuesInnerResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            VenueDetailsService venueDetailsService = serviceGenerator.createService(VenueDetailsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(venueDetailsService.getVenueDetails(getCurrentLanguage(), id,"bearer "+token), false);
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
        return venuesInnerResponseSingle;
    }

    @Override
    public Single<AttractionInnerResponse> getAttractionsDetails(int id,String token) {
        Single<AttractionInnerResponse> venuesInnerResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionInnerService attractionInnerService = serviceGenerator.createService(AttractionInnerService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(attractionInnerService.getAttractionsDetails(getCurrentLanguage(), id,"bearer "+token), false);
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
        return venuesInnerResponseSingle;
    }

    @Override
    public Single<ViewTicketResponse> viewAttractionTickets(String startTime, String endTime, String date, String token) {
        Single<ViewTicketResponse> viewTicketResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ViewTicketsService viewTicketsService = serviceGenerator.createService(ViewTicketsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(viewTicketsService.viewTickets(getCurrentLanguage(),startTime,endTime,date,"bearer "+token), false);
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
        return viewTicketResponseSingle;
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
    public Single<SubscribeResponse> subscribe(String token) {
        Single<SubscribeResponse> subscribeResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SubscribeService subscribeService = serviceGenerator.createService(SubscribeService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(subscribeService.subscribe(getCurrentLanguage(),"bearer "+token), false);
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
        return subscribeResponseSingle;
    }

    @Override
    public Single<LoginResponse> edit(String token,String userName, String email, String dateOfBirth, String gender, String password, String mobile, File image) {
        Single<LoginResponse> editResponseSingle = Single.create(singleOnSubscribe -> {
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
                            if (!TextUtils.isEmpty(email)){
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
                                    "bearer "+token,
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
        return editResponseSingle;
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
    public Single<LikeResponse> likeVenues(int id, String token) {
        Single<LikeResponse> likeResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            VenuesLikeService likeService = serviceGenerator.createService(VenuesLikeService.class, BASE_URL);
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
    public Single<LikeResponse> likeAttractions(int id, String token) {
        Single<LikeResponse> likeResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionsLikeService likeService = serviceGenerator.createService(AttractionsLikeService.class, BASE_URL);
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
                            OrderService orderService = serviceGenerator.createServiceNotNullSerialization(OrderService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(orderService.order(getCurrentLanguage(), "bearer " + token,new OrderRequest(name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total)), false);
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

    @Override
    public Single<SMSResponse> sendSMS(String phone) {
        Single<SMSResponse> smsResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            SMSService smsService = serviceGenerator.createService(SMSService.class, JAWAL_URL);
                            String msg = generatePIN();
                            ServiceResponse serviceResponse = processCall(smsService.sendSMS(USER_NAME,PASSWORD,phone,msg,SENDER), false);
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
        return smsResponseSingle;
    }

    @Override
    public Single<FilterEventsResponse> filterEvents(boolean weeklySuggest, List<Integer> categoryId, int date, Double lat, Double lng) {
        Single<FilterEventsResponse> filterEventsResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            FilterEventsService filterEventsService = serviceGenerator.createService(FilterEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(filterEventsService.filterEvents(getCurrentLanguage(),new FilterEventsRequest(weeklySuggest,categoryId,date,lat,lng)), false);
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
        return filterEventsResponseSingle;
    }

    @Override
    public Single<FilterVenuesResponse> filterVenues(boolean weeklySuggest, List<Integer> categoryId, Double lat, Double lng) {
        Single<FilterVenuesResponse> filterVenuesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            FilterVenuesService filterVenuesService = serviceGenerator.createService(FilterVenuesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(filterVenuesService.filterVenues(getCurrentLanguage(),new FilterVenuesRequest(weeklySuggest,categoryId,lat,lng)), false);
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
        return filterVenuesResponseSingle;
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
                            ServiceResponse serviceResponse = processCall(searchService.search(getCurrentLanguage(),new SearchRequest(searchWord,types,categoryId)), false);
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
        Single<ContactUsResponse> contactUsResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            ContactUsService contactUsService = serviceGenerator.createService(ContactUsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(contactUsService.contactUs(getCurrentLanguage(),"bearer "+token,new ContactUsRequest(subject,message)), false);
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
        return contactUsResponseSingle;
    }

    @Override
    public Single<AttractionOrderResponse> orderAttraction(AttractionOrderRequest attractionOrderRequest, String token) {
        Single<AttractionOrderResponse> attractionOrderResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AttractionOrderService attractionOrderService = serviceGenerator.createService(AttractionOrderService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(attractionOrderService.attractionOrder(getCurrentLanguage(),"bearer "+token,attractionOrderRequest), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
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
        return attractionOrderResponseSingle;
    }


    private String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String generatePIN()
    {

        //generate a 4 digit integer 1000 <10000
        int randomPIN = (int)(Math.random()*9000)+1000;

        //Store integer in a string
        return String.valueOf(randomPIN);
    }
}
