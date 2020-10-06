package com.volkanhotur.basemvvm.extension

import android.util.Base64
import java.nio.charset.StandardCharsets

/**
 * @author volkanhotur
 */

fun String.getJsonFromBase64String(): String {
    val decodedString = Base64.decode(this, Base64.DEFAULT)
    return String(decodedString, StandardCharsets.UTF_8)
}