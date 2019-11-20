package com.volkanhotur.basemvvm.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class ViewHelper {
    private static ViewHelper instance;

    public static ViewHelper getInstance() {
        if(instance == null){
            synchronized (ViewHelper.class){
                instance = new ViewHelper();
            }
        }
        return instance;
    }

    private ViewHelper() { }

    public float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, context.getColor(0xFF888888)), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private double getDeviceInch(WindowManager windowManager){
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        int dens=dm.densityDpi;
        double wi=(double)width/(double)dens;
        double hi=(double)height/(double)dens;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);

        return Math.sqrt(x+y);
    }

    public boolean isMobileDevice(WindowManager windowManager){
        return getDeviceInch(windowManager) < 7;
    }
}
