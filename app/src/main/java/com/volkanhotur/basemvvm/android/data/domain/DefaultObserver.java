package com.volkanhotur.basemvvm.android.data.domain;

import android.widget.Toast;

import com.core.BaseView;
import com.domain.remote.exception.ApiException;
import com.domain.remote.usecase.BaseObserver;

public abstract class DefaultObserver<T> extends BaseObserver<T> {

    private BaseView view;

    protected DefaultObserver(BaseView view) {
        this.view = view;
    }

    protected DefaultObserver() {
        this.view = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (view != null && view.isLoadingBarEnabled())
            view.showLoadingBar();
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (view != null && view.isLoadingBarEnabled())
            view.hideLoadingBar();
    }

    @Override
    public void onError(Throwable exception) {
        super.onError(exception);

        if (view != null && view.isLoadingBarEnabled())
            view.hideLoadingBar();

        if (view != null) {
            if(exception instanceof ApiException){
                 if(((ApiException) exception).getCode() == 401 || ((ApiException) exception).getCode() == 403){
                    view.expireSession();
                }  else {
                     Toast.makeText(view.context(), "We are unable to process at this time. Please try again later.", Toast.LENGTH_LONG).show();
                 }
            }else {
                Toast.makeText(view.context(), "We are unable to process at this time. Please try again later.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
