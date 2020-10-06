package com.volkanhotur.basemvvm.data.domain

import com.volkanhotur.basemvvm.data.repository.ApiDataSource
import com.domain.remote.usecase.AbstractUseCase
import java.util.*

/**
 * @author volkanhotur
 */

abstract class BaseUseCase<T>(var apiDataSource: ApiDataSource) : AbstractUseCase<T, HashMap<String?, Any?>?>() {

    var params: HashMap<String?, Any?> = HashMap()

    fun addParam(key: String, data: Any) {
        params[key] = data
    }

}