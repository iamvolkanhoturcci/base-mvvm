package com.domain.remote.usecase

import io.reactivex.observers.DisposableObserver

/**
 * @author volkanhotur
 */
abstract class AbstractObserver<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {}

    override fun onComplete() {}

    override fun onError(exception: Throwable) {}
}