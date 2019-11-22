package com.volkanhotur.basemvvm.android.data.repository;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author volkanhotur
 */

public class ApiDataFactory implements Repository {

    private ApiDataSource source;

    @Inject
    public ApiDataFactory(ApiDataSource source) {
        this.source = source;
    }

    @Override
    public Observable<ResponseBody> postRequest(String url, HashMap<String, Object> params, HashMap<String, Object> headers) {
        return source.postRequest(url, params, headers);
    }

    @Override
    public Observable<ResponseBody> postRequest(String url, HashMap<String, Object> params) {
        return source.postRequest(url, params);
    }

    @Override
    public Observable<ResponseBody> getRequest(String url, HashMap<String, Object> params, HashMap<String, Object> headers) {
        return source.getRequest(url, params, headers);
    }

    @Override
    public Observable<ResponseBody> getRequest(String url) {
        return source.getRequest(url);
    }
}
