package com.volkanhotur.basemvvm.android.utils.helper

import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * @author volkanhotur
 */
class SharedHelper(private val sharedPreferences: SharedPreferences?, private val gSon: Gson?) {

    private fun addString(key: String, value: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    private fun retrieveString(key: String, defaultValue: String?): String {
        return sharedPreferences?.getString(key, defaultValue) ?: ""
    }

    fun addInt(key: String?, value: Int) {
        val editor = sharedPreferences?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun retrieveInt(key: String?, defaultValue: Int): Int {
        return sharedPreferences?.getInt(key, defaultValue) ?: 0
    }

    fun addFloat(key: String?, value: Float) {
        val editor = sharedPreferences?.edit()
        editor?.putFloat(key, value)
        editor?.apply()
    }

    fun retrieveFloat(key: String?, defaultValue: Float): Float {
        return sharedPreferences?.getFloat(key, defaultValue) ?: 0f
    }

    fun addLong(key: String?, value: Long) {
        val editor = sharedPreferences?.edit()
        editor?.putLong(key, value)
        editor?.apply()
    }

    fun retrieveLong(key: String?, defaultValue: Long): Long {
        return sharedPreferences?.getLong(key, defaultValue) ?: 0
    }

    fun addBoolean(key: String?, value: Boolean) {
        val editor = sharedPreferences?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun retrieveBoolean(key: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences?.getBoolean(key, defaultValue) ?: false
    }

    fun addStringSet(key: String?, value: Set<String?>?) {
        val editor = sharedPreferences?.edit()
        editor?.putStringSet(key, value)
        editor?.apply()
    }

    fun retrieveStringSet(key: String?, defaultValue: Set<String?>?): Set<String> {
        return sharedPreferences?.getStringSet(key, defaultValue) ?: emptySet()
    }

    @Throws(RuntimeException::class)
    fun <T> addData(key: String, data: T?) {
        if (data == null) {
            throw RuntimeException("Data can not be null")
        }
        val json = gSon?.toJson(data)
        if (json != null) {
            addString(key, json)
        } else {
            throw RuntimeException("Data can not be null")
        }
    }

    fun <T> retrieveData(key: String, clazz: Class<T>?): T? {
        sharedPreferences?.let {
            if (sharedPreferences.contains(key)) {
                val json = retrieveString(key, null) ?: return null
                return gSon?.fromJson(json, clazz)
            }
        }

        return null
    }

    fun fetchAll(): Map<String, *> {
        return sharedPreferences?.all!!
    }

    operator fun contains(key: String?): Boolean {
        return sharedPreferences?.contains(key) ?: false
    }

    fun removeItem(key: String?) {
        val editor = sharedPreferences?.edit()
        editor?.remove(key)
        editor?.apply()
    }

    fun clear() {
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }
}