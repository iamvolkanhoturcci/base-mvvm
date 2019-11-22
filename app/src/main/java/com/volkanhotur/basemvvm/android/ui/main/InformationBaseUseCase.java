package com.volkanhotur.basemvvm.android.ui.main;

import com.volkanhotur.basemvvm.android.data.domain.BaseUseCase;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author volkanhotur
 */

public class InformationBaseUseCase extends BaseUseCase<ResponseBody> {

    @Inject
    public InformationBaseUseCase(ApiDataSource apiDataSource) {
        super(apiDataSource);
    }

    @Override
    public Observable<ResponseBody> buildUseCaseObservable(HashMap<String, Object> params) {
        return apiDataSource.getRequest("users/JakeWharton");
    }
}
