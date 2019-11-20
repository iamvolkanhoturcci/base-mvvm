package com.volkanhotur.basemvvm.android.data.domain;

import com.domain.remote.usecase.UseCase;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource;

import java.util.HashMap;

public abstract class DefaultUseCase<T> extends UseCase<T, HashMap<String, Object>> {

    public ApiDataSource apiDataSource;
    public HashMap<String, Object> params;

    public DefaultUseCase(ApiDataSource apiDataSource) {
        this.apiDataSource = apiDataSource;
        this.params = new HashMap<>();
    }

    public void addParam(String key, Object data) {
        this.params.put(key, data);
    }
}
