package com.volkanhotur.basemvvm.ui.view.widget.textinput.validator

/**
 * @author volkanhotur
 */

import java.util.regex.Pattern

class RegexValidator(private val regex: String, errorMessage: String,
                     callback: ValidationCallback? = null) : BaseValidator(errorMessage, callback) {

    override fun isValid(text: String): Boolean {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text).find()
    }
}
