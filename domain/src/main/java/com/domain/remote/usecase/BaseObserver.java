package com.domain.remote.usecase;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) { }

    @Override
    public void onComplete() { }

    @Override
    public void onError(Throwable exception) { }
}