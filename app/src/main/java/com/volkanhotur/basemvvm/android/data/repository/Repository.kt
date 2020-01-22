package com.volkanhotur.basemvvm.android.data.repository

import io.reactivex.Observable
import okhttp3.ResponseBody
import java.util.*

/**
 * @author volkanhotur
 */
interface Repository {

    fun postRequest(url: String?, params: HashMap<String?, Any?>?, headers: HashMap<String?, Any?>?): Observable<ResponseBody?>

    fun postRequest(url: String?, params: HashMap<String?, Any?>?): Observable<ResponseBody?>

    fun getRequest(url: String?, params: HashMap<String?, Any?>?, headers: HashMap<String?, Any?>?): Observable<ResponseBody?>

    fun getRequest(url: String?): Observable<ResponseBody?>
}