package com.volkanhotur.basemvvm.data.domain

import com.volkanhotur.basemvvm.extension.cookieBar
import com.volkanhotur.basemvvm.extension.isInternetAvailable
import com.core.abstractbase.AbstractView
import com.domain.remote.exception.AbstractApiException
import com.domain.remote.usecase.AbstractObserver
import com.volkanhotur.basemvvm.R

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
                if (exception.code == 200 ||  exception.code == 201 || exception.code == 204) {
                    it.context().cookieBar(exception.message)
                    it.navigateView()

                }else if (exception.code == 401 ||  exception.code == 403) {
                    it.expireSession(exception.title, exception.message)

                } else {
                    it.context().cookieBar(R.string.unknown_error)
                    it.navigateView()
                }
            } else if(it.context()?.isInternetAvailable() == false){
                it.context().cookieBar(R.string.network_error)

            } else {
                it.context().cookieBar(R.string.unknown_error)
                it.navigateView()
            }
        }
    }
}