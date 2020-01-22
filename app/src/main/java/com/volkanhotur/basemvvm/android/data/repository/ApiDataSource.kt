package com.volkanhotur.basemvvm.android.data.repository

import com.domain.remote.exception.AbstractApiException
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * @author volkanhotur
 */
class ApiDataSource @Inject constructor(private val api: Api) : Repository {

    override fun postRequest(url: String?, params: HashMap<String?, Any?>?, headers: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return api.postRequest(url, params, headers).flatMap { response: Response<ResponseBody?>? -> interceptError(response) }
    }

    override fun postRequest(url: String?, params: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return api.postRequest(url).flatMap { response: Response<ResponseBody?>? -> interceptError(response) }
    }

    override fun getRequest(url: String?, params: HashMap<String?, Any?>?, headers: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return api.getRequest(url, params, headers).flatMap { response: Response<ResponseBody?>? -> interceptError(response) }
    }

    override fun getRequest(url: String?): Observable<ResponseBody?> {
        return api.getRequest(url).flatMap { response: Response<ResponseBody?>? -> interceptError(response) }
    }

    private fun <T> interceptError(response: Response<T?>?): Observable<T> {
        val requestCode = response!!.code()
        if (requestCode >= 300) {
            if (requestCode == 400) {
                var errorMessage: String? = ""

                try {
                    response.errorBody()?.let {
                        errorMessage = it.string()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                return Observable.error(AbstractApiException(errorMessage!!, "", requestCode))
            }
            return Observable.error(AbstractApiException("", "", requestCode))
        }
        return Observable.just(Objects.requireNonNull(response.body()))

        //TODO("Refactoring Needed!")
    }
}