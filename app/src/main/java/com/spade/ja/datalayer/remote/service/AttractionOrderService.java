package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/18/18.
 */

public interface AttractionOrderService {
    @POST("{lang}/attractions/ticket/order")
    Call<AttractionOrderResponse> attractionOrder(@Path("lang") String lang, @Header("Authorization") String token, @Body AttractionOrderRequest orderRequest);

}
