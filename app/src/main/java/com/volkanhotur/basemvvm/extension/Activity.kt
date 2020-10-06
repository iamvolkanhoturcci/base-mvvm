package com.volkanhotur.basemvvm.extension

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
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

fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

fun Activity?.toastLong(text:  String?) = this?.let { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }

fun Activity?.toastLong(@StringRes textId: Int) = this?.let { Toast.makeText(this, textId, Toast.LENGTH_LONG).show() }

fun Activity?.toastShort(text: String?) = this?.let { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }

fun Activity?.toastShort(@StringRes textId: Int) = this?.let { Toast.makeText(this, textId, Toast.LENGTH_SHORT).show() }

fun Activity?.cookieBar(message: String?){
    this?.let {
        val sneaker = Sneaker.with(this)
        val view = LayoutInflater.from(this).inflate(R.layout.alert_view_top,  sneaker.getView(), false)
        val textView = view.findViewById<TextView>(R.id.text_view_info)
        textView.text = message
        sneaker.sneakCustom(view)
    }
}

fun Activity?.cookieBar(@StringRes messageId: Int){
    this?.let {
        val sneaker = Sneaker.with(this)
        val view = LayoutInflater.from(this).inflate(R.layout.alert_view_top,  sneaker.getView(), false)
        val textView = view.findViewById<TextView>(R.id.text_view_info)
        textView.text = getString(messageId)
        sneaker.sneakCustom(view)
    }
}

fun Activity?.cookieBar(message: String?, @StringRes actionId: Int, confirmListener: DialogConfirmListener?){
    this?.let {
        val sneaker = Sneaker.with(this)
        sneaker.autoHide(false)
        val view = LayoutInflater.from(this).inflate(R.layout.alert_view_top_with_action,  sneaker.getView(), false)
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

fun Activity?.cookieBar(@StringRes messageId: Int, @StringRes actionId: Int, confirmListener: DialogConfirmListener?){
    this?.let {
        val sneaker = Sneaker.with(this)
        sneaker.autoHide(false)
        val view = LayoutInflater.from(this).inflate(R.layout.alert_view_top_with_action,  sneaker.getView(), false)
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

fun Activity.showInfoDialog(title: Int?, message: Int?, positiveText: Int?) {
    this.let {
        MaterialDialog(it).show {
            title(title)
            message(message)
            positiveButton(positiveText)
            cornerRadius(res = R.dimen.default_material_dialog_radius)
        }
    }
}

fun Activity.showChoiceDialog(title: Int?, message: Int?, positiveText: Int?, negativeText: Int?, choiceListener: DialogChoiceListener) {
    this.let {
        MaterialDialog(it).show {
            title(title)
            message(message)
            cancelable(false)
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

fun Activity.showBottomSheetDialogWithItems(message: Int?, negativeText: Int?, items : Int?, selectedItemListener: BottomSheetSelectedItemListener?){
    this.let {
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

fun Activity.showBottomSheetDialogFullScreen(message: Int?){
    this.let {
        MaterialDialog(it, BottomSheet(LayoutMode.MATCH_PARENT)).show {
            message(message)
            cornerRadius(res = R.dimen.default_material_dialog_radius)
        }
    }
}

fun Activity.openPlayStore() {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("")))
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("")))
    }
}