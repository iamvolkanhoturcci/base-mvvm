package com.volkanhotur.basemvvm.android.data.repository;

import com.domain.remote.exception.ApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ApiDataSource implements Repository {

    private Api api;

    @Inject
    public ApiDataSource(Api api) {
        this.api = api;
    }

    @Override
    public Observable<ResponseBody> postRequest(String url, HashMap<String, Object> params, HashMap<String, Object> headers) {
        return api.postRequest(url, params, headers).flatMap(this::interceptError);
    }

    @Override
    public Observable<ResponseBody> postRequest(String url, HashMap<String, Object> params) {
        return api.postRequest(url).flatMap(this::interceptError);
    }

    @Override
    public Observable<ResponseBody> getRequest(String url, HashMap<String, Object> params, HashMap<String, Object> headers) {
        return api.getRequest(url, params, headers).flatMap(this::interceptError);
    }

    @Override
    public Observable<ResponseBody> getRequest(String url) {
        return api.getRequest(url).flatMap(this::interceptError);
    }

    private <T> Observable<T> interceptError(Response<T> response) {
        int requestCode = response.code();

        if (requestCode >= 300) {
            if(requestCode == 400){
                String errorMessage = "";

                try {
                    if (response.errorBody() != null) errorMessage = response.errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return Observable.error(new ApiException(errorMessage, "", requestCode));
            }

            return Observable.error(new ApiException("", "", requestCode));
        }

        return Observable.just(Objects.requireNonNull(response.body()));
    }
}