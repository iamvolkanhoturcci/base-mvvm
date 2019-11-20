package com.volkanhotur.basemvvm.android.data.repository;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface Repository {

    Observable<ResponseBody> postRequest(String url, HashMap<String, Object> params, HashMap<String, Object> headers);

    Observable<ResponseBody> postRequest(String url, HashMap<String, Object> params);

    Observable<ResponseBody> getRequest(String url, HashMap<String, Object> params, HashMap<String, Object> headers);

    Observable<ResponseBody> getRequest(String url);
}
