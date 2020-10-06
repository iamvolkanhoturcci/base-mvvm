package com.volkanhotur.basemvvm.ui.view.widget.textinput.validator

/**
 * @author volkanhotur
 */

class RequiredValidator(errorMessage: String, callback: ValidationCallback? = null) : BaseValidator(errorMessage, callback) {

    override fun isValid(text: String): Boolean {
        return text.isNotEmpty()
    }
}
