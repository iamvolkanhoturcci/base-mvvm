package com.volkanhotur.basemvvm.ui.view.widget.textinput.validator

/**
 * @author volkanhotur
 */

class LengthValidator(private val minimumLength: Int = LENGTH_ZERO,
                      private val maximumLength: Int = LENGTH_INDEFINITE,
                      errorMessage: String,
                      callback: ValidationCallback? = null) : BaseValidator(errorMessage, callback) {

    val minLength: Int
        get() {
            return minimumLength
        }
    val maxLength: Int
        get() {
            return maximumLength
        }

    override fun isValid(text: String): Boolean {
        val length = text.length
        return if (maxLength == LENGTH_INDEFINITE) {
            length >= minLength
        } else {
            length in minLength..maxLength
        }
    }

    companion object {

        const val LENGTH_INDEFINITE = -1

        const val LENGTH_ZERO = 0
    }
}
