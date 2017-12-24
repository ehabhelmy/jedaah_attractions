package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.local.LocalRepository;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.remote.RemoteRepository;
import com.google.android.gms.maps.model.LatLng;

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
            remoteRepository.getTopEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<EventsResponse>() {
                        @Override
                        public void onSuccess(EventsResponse eventsResponse) {
                            localRepository.saveTopEvents(eventsResponse);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.getTopEvents();
        } else {
            return localRepository.getTopEvents();
        }
    }

    @Override
    public Single<EventsResponse> getNearByEvents(LatLng latLng,boolean fresh) {
        if (isConnected(JaApplication.getContext())){
            remoteRepository.getNearByEvents(latLng)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<EventsResponse>() {
                        @Override
                        public void onSuccess(EventsResponse eventsResponse) {
                            localRepository.saveNearByEvents(eventsResponse);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.getNearByEvents(latLng);
        } else {
            return localRepository.getNearByEvents();
        }
    }

    @Override
    public Single<Category> getCategories(boolean fresh) {
        if (fresh) {
            remoteRepository.getCategories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Category>() {

                        @Override
                        public void onSuccess(Category categories) {
                            localRepository.saveCategories(categories);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.getCategories();
        } else {
            return localRepository.getCategories();
        }
    }

    @Override
    public Single<EventsResponse> getTodayEvents(boolean fresh) {
        if (fresh) {
            remoteRepository.getTodayEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<EventsResponse>() {
                        @Override
                        public void onSuccess(EventsResponse eventsResponse) {
                            localRepository.saveTodayEvents(eventsResponse);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.getTodayEvents();
        }
        return localRepository.getTodayEvents();
    }

    @Override
    public Single<EventsResponse> getWeekEvents(boolean fresh) {
        if (fresh) {
            remoteRepository.getWeekEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<EventsResponse>() {
                        @Override
                        public void onSuccess(EventsResponse eventsResponse) {
                            localRepository.saveWeekEvents(eventsResponse);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.getWeekEvents();
        }
        return localRepository.getWeekEvents();
    }

    @Override
    public Single<EventsResponse> getAllEvents(boolean fresh) {
        if (fresh){
            remoteRepository.getTopEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<EventsResponse>() {
                        @Override
                        public void onSuccess(EventsResponse eventsResponse) {
                            localRepository.saveAllEvents(eventsResponse);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
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
            remoteRepository.login(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            if (loginResponse.getSuccess()) {
                                localRepository.saveLoggedUser(loginResponse.getData().getUser());
                                localRepository.saveToken(loginResponse.getData().getToken());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.login(email, password);
    }

    @Override
    public String getToken() {
        return localRepository.getToken();
    }
}
