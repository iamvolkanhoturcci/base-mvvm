package com.volkanhotur.basemvvm.data.repository

import com.volkanhotur.basemvvm.data.model.remote.response.BaseResponse
import com.domain.remote.exception.AbstractApiException
import io.reactivex.Observable
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * @author volkanhotur
 */

class ApiDataSource @Inject constructor(private val api: Api) : Repository {

    private fun <T> interceptError(response: Response<T?>?): Observable<T> {
        val requestCode = response?.code() ?: 500

        if(requestCode == 200 || requestCode == 201 || requestCode == 204){
            val baseResponse = (response?.body() as BaseResponse)

            /*return when {
                baseResponse.succeeded == true -> {
                    Observable.just(Objects.requireNonNull(response?.body()))
                }
                baseResponse.error.isSessionExpired == true -> {
                    return Observable.error(AbstractApiException(
                        baseResponse.error.errorMessage ?: "",
                        baseResponse.error.errorCode ?: "", 401)
                    )
                }
                else -> {
                    Observable.error(AbstractApiException(
                        baseResponse.error.errorMessage ?: "",
                        "", requestCode)
                    )
                }
            }*/
        }

        if (requestCode == 400 || requestCode == 401 || requestCode == 403) {
            var errorMessage: String? = ""

            try {
                response?.errorBody()?.let {
                    errorMessage = it.string()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return Observable.error(AbstractApiException(errorMessage ?: "", "", requestCode))
        }


        return Observable.error(AbstractApiException("", "", requestCode))
    }
}