package com.volkanhotur.basemvvm.extension

import android.animation.Animator
import android.app.Activity
import android.app.KeyguardManager
import android.app.NotificationManager
import android.app.Service
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.content.ActivityNotFoundException
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Build
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.volkanhotur.basemvvm.utils.impl.DialogConfirmListener
import com.irozon.sneaker.Sneaker
import com.volkanhotur.basemvvm.R

/**
 * @author volkanhotur
 */

inline fun <reified T : Activity> Context?.startActivity() = this?.startActivity(Intent(this, T::class.java))

inline fun <reified T : Activity> Context?.startNoStackActivity() {
    this?.let {
        if(it is Activity){
            val intent = Intent(it, T::class.java)
            it.startActivity(intent)
            it.finishAffinity()
        }else{
            val intent = Intent(it, T::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK)
            it.startActivity(intent)
        }
    }
}

inline fun <reified T : Service> Context?.startService() = this?.startService(Intent(this, T::class.java))

inline fun <reified T : Activity> Context?.startActivityWithAnimatedFinish(view: View?){
    this?.let {
        view?.alpha = 1.0f
        view?.animate().apply {
            this?.let {
                interpolator = LinearInterpolator()
                duration = 250
                alpha(0.0f)
                start()
            }

            this?.setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    startActivity<T>()
                }
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
        }
    }
}

inline fun <reified T : Activity> Context?.startNoStackActivityWithAnimatedFinish(view: View?){
    this?.let {
        view?.alpha = 1.0f
        view?.animate().apply {
            this?.let {
                interpolator = LinearInterpolator()
                duration = 250
                alpha(0.0f)
                start()
            }

            this?.setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    startNoStackActivity<T>()
                }
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
        }
    }
}

inline fun <reified T : Activity> Context.startActivityWithAnimation(enterResId: Int = 0, exitResId: Int = 0) {
    val intent = Intent(this, T::class.java)
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

inline fun <reified T : Activity> Context.startActivityWithAnimation(enterResId: Int = 0, exitResId: Int = 0, intentBody: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.intentBody()
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

fun Context?.toastLong(text: String?) = this?.let { Toast.makeText(it, text, Toast.LENGTH_LONG).show() }

fun Context?.toastLong(@StringRes textId: Int) = this?.let { Toast.makeText(it, textId, Toast.LENGTH_LONG).show() }

fun Context?.toastShort(text: String?) = this?.let { Toast.makeText(it, text, Toast.LENGTH_SHORT).show() }

fun Context?.toastShort(@StringRes textId: Int) = this?.let { Toast.makeText(it, textId, Toast.LENGTH_SHORT).show() }

fun Context?.cookieBar(message: String?){
    this?.let {
        if(it is Activity){
            val sneaker = Sneaker.with(it)
            val view = LayoutInflater.from(this).inflate(R.layout.alert_view_top,  sneaker.getView(), false)
            val textView = view.findViewById<TextView>(R.id.text_view_info)
            textView.text = message
            sneaker.sneakCustom(view)
        }
    }
}

fun Context?.cookieBar(@StringRes messageId: Int){
    this?.let {
        if(it is Activity){
            val sneaker = Sneaker.with(it)
            val view = LayoutInflater.from(this).inflate(R.layout.alert_view_top,  sneaker.getView(), false)
            val textView = view.findViewById<TextView>(R.id.text_view_info)
            textView.text = getString(messageId)
            sneaker.sneakCustom(view)
        }
    }
}

fun Context?.cookieBar(message: String?, @StringRes actionId: Int, confirmListener: DialogConfirmListener?){
    this?.let {
        if(it is Activity){
            val sneaker = Sneaker.with(it)
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
}

fun Context?.cookieBar(@StringRes messageId: Int, @StringRes actionId: Int, confirmListener: DialogConfirmListener?){
    this?.let {
        if(it is Activity){
            val sneaker = Sneaker.with(it)
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
}

inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels


inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels


fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

inline val Context.notificationManager: NotificationManager?
    get() = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

inline val Context.keyguardManager: KeyguardManager?
    get() = getSystemService(KEYGUARD_SERVICE) as? KeyguardManager

inline val Context.telephonyManager: TelephonyManager?
    get() = getSystemService(TELEPHONY_SERVICE) as? TelephonyManager

inline val Context.clipboardManager: ClipboardManager?
    get() = getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager

inline val Context.jobScheduler: JobScheduler?
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(JOB_SCHEDULER_SERVICE) as? JobScheduler

inline val Context.devicePolicyManager: DevicePolicyManager?
    get() = getSystemService(DEVICE_POLICY_SERVICE) as? DevicePolicyManager

fun Context.getLayoutInflater() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun Context.share(text: String?, subject: String? = ""): Boolean {
    val intent = Intent()
    intent.type = "text/plain"
    intent.putExtra(EXTRA_SUBJECT, subject)
    intent.putExtra(EXTRA_TEXT, text)
    return try {
        startActivity(createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

fun Context.dial(phoneNumber : String?){
    val intent = Intent(ACTION_DIAL)
    intent.data = Uri.parse(phoneNumber)
    startActivity(intent)
}

fun Context.defaultSharedPreference(): String{
    return "$packageName.android.shared.pref"
}