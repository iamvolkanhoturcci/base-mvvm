package com.volkanhotur.basemvvm.extension

import android.util.Base64
import org.json.JSONObject
import java.nio.charset.StandardCharsets

/**
 * @author volkanhotur
 */

fun JSONObject.getBase64String(): String {
    return Base64.encodeToString(this.toString().toByteArray(StandardCharsets.UTF_8), Base64.DEFAULT)
}