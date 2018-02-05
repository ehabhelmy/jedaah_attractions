package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.order.OrderRequest;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/28/17.
 */

public interface OrderService {
    @POST("{lang}/events/ticket/order")
    Call<OrderResponse> order(@Path("lang") String lang, @Header("Authorization") String token, @Body OrderRequest orderRequest);
}
