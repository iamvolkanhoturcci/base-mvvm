package com.volkanhotur.basemvvm.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author volkanhotur
 */

object DateUtils {

    fun findDate(dateFormat: String?): String? {
        try {
            @SuppressLint("SimpleDateFormat")
            val sdf = SimpleDateFormat(dateFormat, Locale.US)

            val today: String

            val calendar: Calendar = GregorianCalendar(Locale.US)

            today = sdf.format(calendar.time)

            return today
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}