package com.volkanhotur.basemvvm.android.data.repository

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*

/**
 * @author volkanhotur
 */
interface Api {

    @POST
    fun postRequest(@Url url: String?, @Body params: HashMap<String?, Any?>?, @HeaderMap headerMap: HashMap<String?, Any?>?): Observable<Response<ResponseBody?>?>

    @POST
    fun postRequest(@Url url: String?): Observable<Response<ResponseBody?>?>

    @GET
    fun getRequest(@Url url: String?, @QueryMap params: HashMap<String?, Any?>?, @HeaderMap headerMap: HashMap<String?, Any?>?): Observable<Response<ResponseBody?>?>

    @GET
    fun getRequest(@Url url: String?): Observable<Response<ResponseBody?>?>
}