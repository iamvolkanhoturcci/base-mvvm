package com.core.viewmodel

import io.reactivex.disposables.Disposable

/**
 * @author volkanhotur
 */
interface ViewModelProtocol {

    fun addDisposable(disposable: Disposable?)

    fun removeDisposable(disposable: Disposable?)

    fun dispose()

    fun clear()
}