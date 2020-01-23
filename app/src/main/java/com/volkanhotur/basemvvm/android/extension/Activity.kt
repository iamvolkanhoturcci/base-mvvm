package com.volkanhotur.basemvvm.android.extension

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.volkanhotur.alerty.Alerty
import com.volkanhotur.basemvvm.R
import com.volkanhotur.basemvvm.android.utils.impl.DialogChoiceListener

fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

fun Activity?.toastLong(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(this, text, duration).show() }

fun Activity?.toastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(this, textId, duration).show() }

fun Activity?.toastShort(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(this, text, duration).show() }

fun Activity?.toastShort(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(this, textId, duration).show() }

fun Activity.showInfoDialog(title: String?, message: String?, positiveText: String?) {
    Alerty.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancellable(false)
            .setPositiveButtonText(positiveText)
            .setHeaderImage(R.drawable.ic_info_gray)
            .setPositiveButtonColor(-0x7d7d7e)
            .setPositiveButtonTextColor(-0x1)
            .setTitleTextColor(-0x1000000)
            .setMessageTextColor(-0xdededf)
            .setTextAppearance(Alerty.MEDIUM_TEXT)
            .setPositiveListener { obj: Dialog -> obj.dismiss() }
            .setButtonRadius(16f)
            .setDialogRadius(16f)
            .build()
}

fun Activity.showChoiceDialog(title: String?, message: String?, positiveText: String?, negativeText: String?, choiceListener: DialogChoiceListener) {
    Alerty.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancellable(false)
            .setPositiveButtonText(positiveText)
            .setNegativeButtonText(negativeText)
            .setHeaderImage(0)
            .setPositiveButtonColor(-0x7d7d7e)
            .setNegativeButtonColor(-0x7d7d7e)
            .setPositiveButtonTextColor(-0x1)
            .setNegativeButtonTextColor(-0x1)
            .setTitleTextColor(-0x1000000)
            .setMessageTextColor(-0xdededf)
            .setTextAppearance(Alerty.MEDIUM_TEXT)
            .setButtonRadius(16f)
            .setDialogRadius(16f)
            .setPositiveListener { dialog: Dialog? -> choiceListener.positiveClickListener(dialog) }
            .setNegativeListener { dialog: Dialog? -> choiceListener.negativeClickListener(dialog) }
            .build()
}

fun Activity.openPlayStore() {
    val appPackageName = packageName
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("" + appPackageName)))
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("" + appPackageName)))
    }
}