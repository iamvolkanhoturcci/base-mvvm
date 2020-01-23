package com.volkanhotur.basemvvm.android.base.exception

import android.app.Activity
import android.os.Process
import kotlin.system.exitProcess

/**
 * @author volkanhotur
 */
class UnCaughtExceptionHandler(private val activity: Activity) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(thread: Thread, ex: Throwable) {
        try {
            Thread.sleep(50)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        activity.finishAffinity()
        Process.killProcess(Process.myPid())
        exitProcess(1)
    }

}