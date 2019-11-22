package com.volkanhotur.basemvvm.android.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.core.abstractbase.AbstractView;
import com.core.viewmodel.AbstractViewModel;
import com.volkanhotur.basemvvm.android.data.domain.BaseObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;

/**
 * @author volkanhotur
 */

public class MainViewModel extends AbstractViewModel {

    @Inject
    InformationBaseUseCase informationUseCase;

    private MutableLiveData<String> information = new MutableLiveData<>();

    @Inject
    MainViewModel(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

    public MutableLiveData<String> getInformation() {
        return information;
    }

    public void getInformationFromRemote(AbstractView abstractView){
        informationUseCase.execute(new BaseObserver<ResponseBody>(abstractView) {
            @Override
            public void onNext(ResponseBody responseBody) {
                super.onNext(responseBody);

                try {
                    String data = responseBody.string();
                    JSONObject jsonObject = new JSONObject(data);
                    information.postValue(jsonObject.toString());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new HashMap<>());
    }
}
