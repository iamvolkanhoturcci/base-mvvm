package com.volkanhotur.basemvvm.android.extension

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.volkanhotur.alerty.Alerty
import com.volkanhotur.basemvvm.R
import com.volkanhotur.basemvvm.android.utils.impl.DialogChoiceListener

fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Fragment?.toastLong(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(activity, text, duration).show() }

fun Fragment?.toastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(activity, textId, duration).show() }

fun Fragment?.toastShort(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(activity, text, duration).show() }

fun Fragment?.toastShort(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(activity, textId, duration).show() }

fun Fragment.showInfoDialog(title: String?, message: String?, positiveText: String?) {
    activity?.let {
        Alerty.Builder(it)
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
}

fun Fragment.showChoiceDialog(title: String?, message: String?, positiveText: String?, negativeText: String?, choiceListener: DialogChoiceListener) {
    activity?.let {
        Alerty.Builder(it)
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
}

fun Fragment.openPlayStore() {
    activity?.let {
        val appPackageName = it.packageName
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("" + appPackageName)))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("" + appPackageName)))
        }
    }
}