package com.domain.remote.usecase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author volkanhotur
 */

public abstract class AbstractUseCase<T, P> {

    private Disposable usaCaseDisposable;
    private AbstractObserver<T> disposableObserver;

    public AbstractUseCase() { }

    public abstract Observable<T> buildUseCaseObservable(@Nullable P params);

    public Disposable execute(AbstractObserver<T> observer, @Nullable P params) {

        this.disposableObserver = observer;

        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        usaCaseDisposable = observable.subscribeWith(disposableObserver);

        return usaCaseDisposable;
    }

    public void dispose() {
        if (!usaCaseDisposable.isDisposed()) {
            usaCaseDisposable.dispose();
        }
    }

    public void disposeObserver() {
        if (!disposableObserver.isDisposed()) {
            disposableObserver.dispose();
        }
    }
}