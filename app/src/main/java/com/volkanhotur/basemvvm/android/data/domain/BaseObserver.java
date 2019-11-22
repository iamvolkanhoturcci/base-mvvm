package com.volkanhotur.basemvvm.android.data.domain;

import android.widget.Toast;

import com.core.abstractbase.AbstractView;
import com.domain.remote.exception.AbstractApiException;
import com.domain.remote.usecase.AbstractObserver;

/**
 * @author volkanhotur
 */

public abstract class BaseObserver<T> extends AbstractObserver<T> {

    private AbstractView view;

    protected BaseObserver(AbstractView view) {
        this.view = view;
    }

    protected BaseObserver() {
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
            if(exception instanceof AbstractApiException){
                 if(((AbstractApiException) exception).getCode() == 401 || ((AbstractApiException) exception).getCode() == 403){
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
