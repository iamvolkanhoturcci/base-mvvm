package com.domain.remote.exception

/**
 * @author volkanhotur
 */
class AbstractApiException(override var message: String, var title: String, var code: Int) : Exception(message)