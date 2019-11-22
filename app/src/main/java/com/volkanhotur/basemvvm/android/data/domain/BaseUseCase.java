package com.volkanhotur.basemvvm.android.data.domain;

import com.domain.remote.usecase.AbstractUseCase;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource;

import java.util.HashMap;

/**
 * @author volkanhotur
 */

public abstract class BaseUseCase<T> extends AbstractUseCase<T, HashMap<String, Object>> {

    public ApiDataSource apiDataSource;
    public HashMap<String, Object> params;

    public BaseUseCase(ApiDataSource apiDataSource) {
        this.apiDataSource = apiDataSource;
        this.params = new HashMap<>();
    }

    public void addParam(String key, Object data) {
        this.params.put(key, data);
    }
}
