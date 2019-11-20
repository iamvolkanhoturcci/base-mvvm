package com.core.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel implements ViewModelProtocol {

    private CompositeDisposable compositeDisposable;

    public BaseViewModel(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    @Override
    public void removeDisposable(Disposable disposable) {
        this.compositeDisposable.remove(disposable);
    }

    @Override
    public void dispose() {
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.dispose();
    }
}
