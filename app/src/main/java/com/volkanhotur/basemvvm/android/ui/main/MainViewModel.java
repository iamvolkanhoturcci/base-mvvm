package com.volkanhotur.basemvvm.android.ui.main;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.core.BaseView;
import com.core.viewmodel.BaseViewModel;
import com.volkanhotur.basemvvm.android.data.domain.DefaultObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;

public class MainViewModel extends BaseViewModel {

    @Inject
    InformationUseCase informationUseCase;

    private MutableLiveData<String> information = new MutableLiveData<>();

    @Inject
    MainViewModel(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

    public MutableLiveData<String> getInformation() {
        return information;
    }

    public void getInformationFromRemote(BaseView baseView){
        informationUseCase.execute(new DefaultObserver<ResponseBody>(baseView) {
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
