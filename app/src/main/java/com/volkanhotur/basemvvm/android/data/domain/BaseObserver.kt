package com.volkanhotur.basemvvm.android.data.domain

import android.widget.Toast
import com.core.abstractbase.AbstractView
import com.domain.remote.exception.AbstractApiException
import com.domain.remote.usecase.AbstractObserver
import com.volkanhotur.basemvvm.R
import com.volkanhotur.basemvvm.android.extension.toastLong

/**
 * @author volkanhotur
 */
abstract class BaseObserver<T> : AbstractObserver<T> {

    private var view: AbstractView?

    protected constructor(view: AbstractView?) {
        this.view = view
    }

    protected constructor() {
        view = null
    }

    override fun onStart() {
        super.onStart()

        view?.let {
            if (it.isLoadingBarEnabled) {
                it.showLoadingBar()
            }
        }
    }

    override fun onNext(t: T) {
        super.onNext(t)
    }

    override fun onComplete() {
        super.onComplete()

        view?.let {
            if (it.isLoadingBarEnabled) {
                it.hideLoadingBar()
            }
        }
    }

    override fun onError(exception: Throwable) {
        super.onError(exception)

        view?.let {
            if (it.isLoadingBarEnabled) {
                it.hideLoadingBar()
            }

            if (exception is AbstractApiException) {
                if (exception.code == 401 || exception.code == 403) {
                    it.expireSession()
                } else {
                    it.context().toastLong(R.string.unknown_error)
                }
            } else {
                it.context().toastLong(R.string.unknown_error)
            }
        }
    }
}