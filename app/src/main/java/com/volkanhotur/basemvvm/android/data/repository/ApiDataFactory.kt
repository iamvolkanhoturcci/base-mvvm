package com.volkanhotur.basemvvm.android.data.repository

import io.reactivex.Observable
import okhttp3.ResponseBody
import java.util.*
import javax.inject.Inject

/**
 * @author volkanhotur
 */
class ApiDataFactory @Inject constructor(private val source: ApiDataSource) : Repository {

    override fun postRequest(url: String?, params: HashMap<String?, Any?>?, headers: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return source.postRequest(url, params, headers)
    }

    override fun postRequest(url: String?, params: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return source.postRequest(url, params)
    }

    override fun getRequest(url: String?, params: HashMap<String?, Any?>?, headers: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return source.getRequest(url, params, headers)
    }

    override fun getRequest(url: String?): Observable<ResponseBody?> {
        return source.getRequest(url)
    }
}