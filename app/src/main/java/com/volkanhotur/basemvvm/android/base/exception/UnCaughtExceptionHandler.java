package com.volkanhotur.basemvvm.android.base.exception;

import android.app.Activity;

public class UnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Activity activity;

    public UnCaughtExceptionHandler(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.finishAffinity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
