package com.core.viewmodel;

import io.reactivex.disposables.Disposable;

/**
 * @author volkanhotur
 */

public interface ViewModelProtocol {

    void addDisposable(Disposable disposable);

    void removeDisposable(Disposable disposable);

    void dispose();

    void clear();
}
