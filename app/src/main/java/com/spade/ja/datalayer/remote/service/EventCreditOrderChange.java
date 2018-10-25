package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.request.changeorder.ChangeOrderRequest;
import com.spade.ja.datalayer.pojo.request.order.OrderRequest;
import com.spade.ja.datalayer.pojo.response.eventcreditconfirm.EventChangeStatusResponse;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventCreditOrderChange {
    @POST("{lang}/payment/event/change")
    Call<EventChangeStatusResponse> changeOrder(@Path("lang") String lang, @Header("Authorization") String token, @Body ChangeOrderRequest orderRequest);

}
