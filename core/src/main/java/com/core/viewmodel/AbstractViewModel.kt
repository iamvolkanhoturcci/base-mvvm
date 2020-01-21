package com.core.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author volkanhotur
 */
abstract class AbstractViewModel(private val compositeDisposable: CompositeDisposable?) : ViewModel(), ViewModelProtocol {

    override fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            compositeDisposable?.add(it)
        }
    }

    override fun removeDisposable(disposable: Disposable?) {
        disposable?.let {
            compositeDisposable?.remove(it)
        }
    }

    override fun dispose() {
        compositeDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun clear() {
        compositeDisposable?.clear()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}