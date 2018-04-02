package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.viewtickets.ViewTicketResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ehab on 3/17/18.
 */

public interface ViewTicketsService {

    @GET("{lang}/attractions/ticket/view")
    Call<ViewTicketResponse> viewTickets(@Path("lang") String lang, @Query("start_time") String startTime,@Query("end_time")String endTime,@Query("date") String date, @Header("Authorization") String token);

}
