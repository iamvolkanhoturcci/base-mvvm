package com.volkanhotur.basemvvm.android.data.repository;

import java.util.HashMap;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author volkanhotur
 */

public interface Api {

    @POST
    Observable<Response<ResponseBody>> postRequest(@Url String url, @Body HashMap<String, Object> params, @HeaderMap HashMap<String, Object> headerMap);

    @POST
    Observable<Response<ResponseBody>> postRequest(@Url String url);

    @GET
    Observable<Response<ResponseBody>> getRequest(@Url String url, @QueryMap HashMap<String, Object> params, @HeaderMap HashMap<String, Object> headerMap);

    @GET
    Observable<Response<ResponseBody>> getRequest(@Url String url);

}
