package com.volkanhotur.basemvvm.extension

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.list.listItems
import com.volkanhotur.basemvvm.utils.impl.BottomSheetSelectedItemListener
import com.volkanhotur.basemvvm.utils.impl.DialogChoiceListener
import com.volkanhotur.basemvvm.utils.impl.DialogConfirmListener
import com.irozon.sneaker.Sneaker
import com.volkanhotur.basemvvm.R

/**
 * @author volkanhotur
 */

fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Fragment?.toastLong(text: String?) = this?.let { Toast.makeText(activity, text, Toast.LENGTH_SHORT).show() }

fun Fragment?.toastLong(@StringRes textId: Int) = this?.let { Toast.makeText(activity, textId, Toast.LENGTH_LONG).show() }

fun Fragment?.toastShort(text: String?) = this?.let { Toast.makeText(activity, text, Toast.LENGTH_SHORT).show() }

fun Fragment?.toastShort(@StringRes textId: Int) = this?.let { Toast.makeText(activity, textId, Toast.LENGTH_SHORT).show() }

fun Fragment?.cookieBar(message: String?){
    this?.let {
        val sneaker = Sneaker.with(this)
        val view = LayoutInflater.from(activity).inflate(R.layout.alert_view_top,  sneaker.getView(), false)
        val textView = view.findViewById<TextView>(R.id.text_view_info)
        textView.text = message
        sneaker.sneakCustom(view)
    }
}

fun Fragment?.cookieBar(@StringRes messageId: Int){
    this?.let {
        val sneaker = Sneaker.with(this)
        val view = LayoutInflater.from(activity).inflate(R.layout.alert_view_top,  sneaker.getView(), false)
        val textView = view.findViewById<TextView>(R.id.text_view_info)
        textView.text = getString(messageId)
        sneaker.sneakCustom(view)
    }
}

fun Fragment?.cookieBar(message: String?, @StringRes actionId: Int, confirmListener: DialogConfirmListener?){
    this?.let {
        val sneaker = Sneaker.with(this)
        sneaker.autoHide(false)
        val view = LayoutInflater.from(activity).inflate(R.layout.alert_view_top_with_action,  sneaker.getView(), false)
        val textView = view.findViewById<TextView>(R.id.text_view_info)
        textView.text = message
        val button = view.findViewById<Button>(R.id.button_action)
        button.text = getString(actionId)
        button.setOnClickListener {
            confirmListener?.onConfirmClick()
        }
        sneaker.sneakCustom(view)
    }
}

fun Fragment?.cookieBar(@StringRes messageId: Int, @StringRes actionId: Int, confirmListener: DialogConfirmListener?){
    this?.let {
        val sneaker = Sneaker.with(this)
        sneaker.autoHide(false)
        val view = LayoutInflater.from(activity).inflate(R.layout.alert_view_top_with_action,  sneaker.getView(), false)
        val textView = view.findViewById<TextView>(R.id.text_view_info)
        textView.text = getString(messageId)
        val button = view.findViewById<Button>(R.id.button_action)
        button.text = getString(actionId)
        button.setOnClickListener {
            confirmListener?.onConfirmClick()
        }
        sneaker.sneakCustom(view)
    }
}

fun Fragment.showInfoDialog(title: Int?, message: Int?, positiveText: Int?) {
    activity?.let {
        MaterialDialog(it).show {
            title(title)
            message(message)
            positiveButton(positiveText)
            cornerRadius(res = R.dimen.default_material_dialog_radius)
        }
    }
}

fun Fragment.showChoiceDialog(title: Int?, message: Int?, positiveText: Int?, negativeText: Int?, choiceListener: DialogChoiceListener) {
    activity?.let {
        MaterialDialog(it).show {
            title(title)
            message(message)
            cornerRadius(res = R.dimen.default_material_dialog_radius)
            positiveButton(positiveText) { dialog ->
                choiceListener.positiveClickListener(dialog)
            }

            negativeButton(negativeText) { dialog ->
                choiceListener.negativeClickListener(dialog)
            }
        }
    }
}

fun Fragment.showBottomSheetDialogWithItems(message: Int?, negativeText: Int?, items : Int?, selectedItemListener: BottomSheetSelectedItemListener?){
    activity?.let {
        MaterialDialog(it, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            message(message)
            negativeButton(negativeText)
            cornerRadius(res = R.dimen.default_material_dialog_radius)
            listItems(items) { dialog, index, text ->
                selectedItemListener?.selectedItem(index, text.toString(), dialog)
                dismiss()
            }
        }
    }
}

fun Fragment.showBottomSheetDialogFullScreen(message: Int?){
    activity?.let {
        MaterialDialog(it, BottomSheet(LayoutMode.MATCH_PARENT)).show {
            message(message)
            cornerRadius(res = R.dimen.default_material_dialog_radius)
        }
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