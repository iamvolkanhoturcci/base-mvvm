package com.volkanhotur.basemvvm.android.utils;

import android.app.Dialog;
import android.content.Context;

import com.volkanhotur.alerty.Alerty;
import com.volkanhotur.basemvvm.R;
import com.volkanhotur.basemvvm.android.utils.impl.DialogChoiceListener;

public class DialogUtils {

    private DialogUtils(){ }

    public static void showInfoDialog(Context context, String title, String message, String positiveText){
        new Alerty.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancellable(false)
                .setPositiveButtonText(positiveText)
                .setHeaderImage(R.drawable.ic_info_gray)
                .setPositiveButtonColor(0XFF828282)
                .setPositiveButtonTextColor(0xFFFFFFFF)
                .setTitleTextColor(0xFF000000)
                .setMessageTextColor(0xFF212121)
                .setTextAppearance(Alerty.MEDIUM_TEXT)
                .setPositiveListener(Dialog::dismiss)
                .setButtonRadius(16f)
                .setDialogRadius(16f)
                .build();
    }

    public static void showChoiceDialog(Context context, String title, String message, String positiveText,
                                        String negativeText, DialogChoiceListener choiceListener){
        new Alerty.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancellable(false)
                .setPositiveButtonText(positiveText)
                .setNegativeButtonText(negativeText)
                .setHeaderImage(0)
                .setPositiveButtonColor(0XFF828282)
                .setNegativeButtonColor(0XFF828282)
                .setPositiveButtonTextColor(0xFFFFFFFF)
                .setNegativeButtonTextColor(0xFFFFFFFF)
                .setTitleTextColor(0xFF000000)
                .setMessageTextColor(0xFF212121)
                .setTextAppearance(Alerty.MEDIUM_TEXT)
                .setButtonRadius(16f)
                .setDialogRadius(16f)
                .setPositiveListener(choiceListener::positiveClickListener)
                .setNegativeListener(choiceListener::negativeClickListener)
                .build();
    }
}
