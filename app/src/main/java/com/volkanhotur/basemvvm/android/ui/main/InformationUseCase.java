package com.volkanhotur.basemvvm.android.ui.main;

import com.volkanhotur.basemvvm.android.data.domain.DefaultUseCase;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class InformationUseCase extends DefaultUseCase<ResponseBody> {

    @Inject
    public InformationUseCase(ApiDataSource apiDataSource) {
        super(apiDataSource);
    }

    @Override
    public Observable<ResponseBody> buildUseCaseObservable(HashMap<String, Object> params) {
        return apiDataSource.getRequest("users/JakeWharton");
    }
}
