package com.volkanhotur.basemvvm.android.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public final class SnackBarUtils {

    private SnackBarUtils() { }

    public static void showSnackbar(View view, String message) {
        if (view == null || message == null) return;

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
