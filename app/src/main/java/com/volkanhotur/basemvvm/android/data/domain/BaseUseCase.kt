package com.volkanhotur.basemvvm.android.data.domain

import com.domain.remote.usecase.AbstractUseCase
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource
import java.util.*

/**
 * @author volkanhotur
 */
abstract class BaseUseCase<T>(var apiDataSource: ApiDataSource) : AbstractUseCase<T, HashMap<String?, Any?>?>() {

    private var params: HashMap<String, Any> = HashMap()

    fun addParam(key: String, data: Any) {
        params[key] = data
    }

}