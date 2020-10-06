package com.domain.remote.usecase

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author volkanhotur
 */
abstract class AbstractUseCase<T, P> {

    private var usaCaseDisposable: Disposable? = null

    private var disposableObserver: AbstractObserver<T>? = null

    abstract fun buildUseCaseObservable(any: Any?, params: P?): Observable<T>

    fun execute(observer: AbstractObserver<T>?, any: Any?, params: P?) {
        disposableObserver = observer

        val observable = buildUseCaseObservable(any, params)
            .subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        usaCaseDisposable = observable.subscribeWith(disposableObserver)
    }

    fun dispose() {
        usaCaseDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun disposeObserver() {
        disposableObserver?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}