package com.volkanhotur.basemvvm.android.extension

import android.util.Base64
import java.nio.charset.StandardCharsets

fun String.getJsonFromBase64String(): String {
    val decodedString = Base64.decode(this, Base64.DEFAULT)
    return String(decodedString, StandardCharsets.UTF_8)
}