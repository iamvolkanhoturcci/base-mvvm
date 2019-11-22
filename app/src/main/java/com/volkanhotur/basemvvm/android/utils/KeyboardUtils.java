package com.volkanhotur.basemvvm.android.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * @author volkanhotur
 */

public final class KeyboardUtils {

    private KeyboardUtils() { }

    public static void hideKeyboard(Activity act) {
        if (act != null && act.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0);
        }
    }
}