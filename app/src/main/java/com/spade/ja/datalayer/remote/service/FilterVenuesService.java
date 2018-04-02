package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.filter.filterevents.FilterEventsRequest;
import com.spade.ja.datalayer.pojo.request.filter.filtervenues.FilterVenuesRequest;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 2/23/18.
 */

public interface FilterVenuesService {

    @POST("{lang}/filter/venue")
    Call<FilterVenuesResponse> filterVenues(@Path("lang") String lang, @Body FilterVenuesRequest filterVenuesRequest);

}
