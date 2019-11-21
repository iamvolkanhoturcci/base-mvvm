package com.volkanhotur.basemvvm.android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class AppUtils {

    private AppUtils() { }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("" + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("" + appPackageName)));
        }
    }
}
