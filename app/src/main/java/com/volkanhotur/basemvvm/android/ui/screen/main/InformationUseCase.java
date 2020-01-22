package com.volkanhotur.basemvvm.android.ui.screen.main;

import com.volkanhotur.basemvvm.android.data.domain.BaseUseCase;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @author volkanhotur
 */

public class InformationUseCase extends BaseUseCase<ResponseBody> {

    @Inject
    public InformationUseCase(ApiDataSource apiDataSource) {
        super(apiDataSource);
    }

    @NotNull
    @Override
    public Observable<ResponseBody> buildUseCaseObservable(HashMap<String, Object> params) {
        return getApiDataSource().getRequest("users/JakeWharton");
    }
}
