package com.core.abstractbase;

import android.content.Context;

/**
 * @author volkanhotur
 */

public interface AbstractView {

    void showLoadingBar();

    void hideLoadingBar();

    boolean isLoadingBarEnabled();

    void expireSession();

    void navigateView();

    Context context();
}
