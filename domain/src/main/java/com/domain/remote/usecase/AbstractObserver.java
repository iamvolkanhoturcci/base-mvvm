package com.domain.remote.usecase;

import io.reactivex.observers.DisposableObserver;

/**
 * @author volkanhotur
 */

public abstract class AbstractObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) { }

    @Override
    public void onComplete() { }

    @Override
    public void onError(Throwable exception) { }
}