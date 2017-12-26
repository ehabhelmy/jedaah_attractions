package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.local.LocalRepository;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.User;
import com.example.ehab.japroject.datalayer.remote.RemoteRepository;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.ehab.japroject.util.NetworkUtils.isConnected;

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
    public Single<DataResponse> getData(boolean fresh) {
        //TODO : first check if there is internet connection ..
        //TODO : if there is internet connection then call remoteRepository.getData then call localRepository.saveData(Key,BaseModel)
        //TODO : if there isn't internet connection then call localRepository.getData
        //TODO : create Single that sends BaseModel in on success.
        //TODO : incase of BaseModel == null ----> call on Error.
        return null;
    }

    @Override
    public Single<EventsResponse> getTopEvents(boolean fresh) {
        if (fresh){
            return remoteRepository.getTopEvents();
        } else {
            return localRepository.getTopEvents();
        }
    }

    @Override
    public Single<EventsResponse> getNearByEvents(LatLng latLng,boolean fresh) {
        if (fresh){
            return remoteRepository.getNearByEvents(latLng);
        } else {
            return localRepository.getNearByEvents();
        }
    }

    @Override
    public Single<Category> getCategories(boolean fresh) {
        if (fresh) {
            return remoteRepository.getCategories();
        } else {
            return localRepository.getCategories();
        }
    }

    @Override
    public Single<EventsResponse> getTodayEvents(boolean fresh) {
        if (fresh) {
            return remoteRepository.getTodayEvents();
        }else {
            return localRepository.getTodayEvents();
        }
    }

    @Override
    public Single<EventsResponse> getWeekEvents(boolean fresh) {
        if (fresh) {
            return remoteRepository.getWeekEvents();
        }else {
            return localRepository.getWeekEvents();
        }
    }

    @Override
    public Single<EventsResponse> getAllEvents(boolean fresh) {
        if (fresh){
            return remoteRepository.getAllEvents();
        } else {
            return localRepository.getAllEvents();
        }
    }

    @Override
    public Single<EventInnerResponse> getEventDetails(int id) {
        return remoteRepository.getEventDetails(id);
    }

    @Override
    public Single<LoginResponse> login(String email, String password) {
        return remoteRepository.login(email, password);
    }

    @Override
    public Single<LoginResponse> sociaLogin(String facebookId,String googleId, String email) {
        return remoteRepository.sociaLogin(facebookId,googleId, email);
    }

    @Override
    public String getToken() {
        return localRepository.getToken();
    }

    @Override
    public Single<RegisterationResponse> register(String userName, String email, String password, String mobile, File image) {
        return remoteRepository.register(userName, email, password, mobile, image);
    }

    @Override
    public Single<LikeResponse> like(int id) {
        return remoteRepository.like(id ,getToken());
    }

    @Override
    public void saveTopEvents(EventsResponse eventsResponse) {
        localRepository.saveTopEvents(eventsResponse);
    }

    @Override
    public void saveNearByEvents(EventsResponse eventsResponse) {
        localRepository.saveNearByEvents(eventsResponse);
    }

    @Override
    public void saveCategories(Category categoriesResponse) {
        localRepository.saveCategories(categoriesResponse);
    }

    @Override
    public void saveTodayEvents(EventsResponse eventsResponse) {
        localRepository.saveTodayEvents(eventsResponse);
    }

    @Override
    public void saveWeekEvents(EventsResponse eventsResponse) {
        localRepository.saveWeekEvents(eventsResponse);
    }

    @Override
    public void saveAllEvents(EventsResponse eventsResponse) {
        localRepository.saveAllEvents(eventsResponse);
    }

    @Override
    public void saveLoggedUser(User user) {
        localRepository.saveLoggedUser(user);
    }

    @Override
    public void saveToken(String token) {
        localRepository.saveToken(token);
    }

}
