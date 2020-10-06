package com.volkanhotur.basemvvm.ui.view.widget.textinput.validator

/**
 * @author volkanhotur
 */

abstract class BaseValidator(val errorMessage: String, val callback: ValidationCallback? = null) {

    fun validate(text: String): Boolean {
        val status = isValid(text)

        callback?.onValidation(status)

        return status
    }

    abstract fun isValid(text: String): Boolean
}
