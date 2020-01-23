package com.volkanhotur.basemvvm.android.ui.screen.main

import com.volkanhotur.basemvvm.android.data.domain.BaseUseCase
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource
import io.reactivex.Observable
import okhttp3.ResponseBody
import java.util.*
import javax.inject.Inject

/**
 * @author volkanhotur
 */
class InformationUseCase @Inject constructor(apiDataSource: ApiDataSource?) : BaseUseCase<ResponseBody?>(apiDataSource!!) {
    override fun buildUseCaseObservable(params: HashMap<String?, Any?>?): Observable<ResponseBody?> {
        return apiDataSource.getRequest("users/JakeWharton")
    }
}