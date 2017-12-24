package com.example.ehab.japroject.datalayer.remote;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ehab on 12/2/17.
 */

@Singleton
public class ServiceGenerator {

    //Network constants
    private final int TIMEOUT_CONNECT = 30;   //In seconds
    private final int TIMEOUT_READ = 30;   //In seconds
    private final String CONTENT_TYPE = "Content-Type";
    private final String API_KEY = "apikey";
    private String CONTENT_TYPE_VALUE = "application/json";
    private final String API_KEY_VALUE = "0bac85d8945140b3bc8dde8aff16e329";

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private Gson gson;

    @Inject
    public ServiceGenerator(Gson gson) {
        this.okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(headerInterceptor)
                     .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                     .connectTimeout(TIMEOUT_CONNECT,TimeUnit.SECONDS);
        this.gson = gson;
    }

    public <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(serviceClass);
    }

    Interceptor headerInterceptor = chain -> {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .header(API_KEY, API_KEY_VALUE)
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    };

    public void setCONTENT_TYPE_VALUE(String CONTENT_TYPE_VALUE) {
        this.CONTENT_TYPE_VALUE = CONTENT_TYPE_VALUE;
    }
}
