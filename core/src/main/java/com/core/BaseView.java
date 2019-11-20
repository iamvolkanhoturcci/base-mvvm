package com.core;

import android.content.Context;

public interface BaseView {

    void showLoadingBar();

    void hideLoadingBar();

    boolean isLoadingBarEnabled();

    void expireSession();

    void navigateView();

    Context context();
}
