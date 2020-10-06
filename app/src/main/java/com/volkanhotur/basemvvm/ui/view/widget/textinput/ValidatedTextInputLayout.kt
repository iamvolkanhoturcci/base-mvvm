package com.volkanhotur.basemvvm.ui.view.widget.textinput

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.volkanhotur.basemvvm.ui.view.widget.textinput.validator.BaseValidator
import com.volkanhotur.basemvvm.ui.view.widget.textinput.validator.LengthValidator
import com.volkanhotur.basemvvm.ui.view.widget.textinput.validator.RegexValidator
import com.volkanhotur.basemvvm.ui.view.widget.textinput.validator.RequiredValidator
import com.google.android.material.textfield.TextInputLayout
import com.volkanhotur.basemvvm.R
import java.util.*

/**
 * @author volkanhotur
 */

class ValidatedTextInputLayout : TextInputLayout {
    private var validators: MutableList<BaseValidator>? = null

    var isAutoValidated = false
        private set

    var isAutoTrimEnabled = false
        private set

    private var errorAlwaysEnabled = true

    val value: String
        get() = if (isAutoTrimEnabled)
            editText?.text.toString().trim { it <= ' ' }
        else
            editText?.text.toString()

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
        initializeCustomAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
        initializeCustomAttrs(context, attrs)
    }

    private fun initialize() {
        if (!isInEditMode) {
            validators = ArrayList()
            this.post {
                editText?.let {
                    if (!it.isInEditMode)
                        initializeTextWatcher()
                }
            }
        }
    }
    private fun initializeCustomAttrs(context: Context, attrs: AttributeSet) {
        if (!isInEditMode) {
            val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable
                    .ValidatedInputTextLayout, 0, 0)

            try {
                isAutoTrimEnabled = typedArray.getBoolean(R.styleable.ValidatedInputTextLayout_autoTrim, false)
                isAutoValidated = typedArray.getBoolean(R.styleable.ValidatedInputTextLayout_autoValidate, false)
                errorAlwaysEnabled = typedArray.getBoolean(R.styleable.ValidatedInputTextLayout_errorAlwaysEnabled, true)

                initRequiredValidation(context, typedArray)
                initLengthValidation(context, typedArray)
                initRegexValidation(context, typedArray)
            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun initRequiredValidation(context: Context, typedArray: TypedArray) {
        if (typedArray.getBoolean(R.styleable.ValidatedInputTextLayout_isRequired, false)) {
            var errorMessage = typedArray.getString(R.styleable.ValidatedInputTextLayout_requiredValidationMessage)
            if (errorMessage == null)
                errorMessage = context.getString(R.string.default_required_validation_message)
            addValidator(RequiredValidator(errorMessage ?: "Error Occurred"))
        }
    }

    private fun initLengthValidation(context: Context, typedArray: TypedArray) {
        val minLength = typedArray.getInteger(R.styleable.ValidatedInputTextLayout_minLength, LengthValidator.LENGTH_ZERO)
        val maxLength = typedArray.getInteger(R.styleable.ValidatedInputTextLayout_maxLength, LengthValidator.LENGTH_INDEFINITE)

        if (!(minLength == LengthValidator.LENGTH_ZERO && maxLength == LengthValidator.LENGTH_INDEFINITE)) {
            var errorMessage = typedArray.getString(R.styleable.ValidatedInputTextLayout_lengthValidationMessage)
            errorMessage?.let {
                errorMessage = when {
                    minLength == LengthValidator.LENGTH_ZERO -> {
                        context.getString(R.string.default_required_length_message_max, maxLength)
                    }
                    maxLength == LengthValidator.LENGTH_INDEFINITE -> {
                        context.getString(R.string.default_required_length_message_min, minLength)
                    }
                    else -> {
                        context.getString(R.string.default_required_length_message_min_max, minLength, maxLength)
                    }
                }
            }
            addValidator(LengthValidator(minLength, maxLength, errorMessage ?: "Error Occurred"))
        }
    }

    private fun initRegexValidation(context: Context, typedArray: TypedArray) {
        val regex = typedArray.getString(R.styleable.ValidatedInputTextLayout_regex)
        regex?.let {
            var errorMessage = typedArray.getString(R.styleable.ValidatedInputTextLayout_regexValidationMessage)
            if (errorMessage == null)
                errorMessage = context.getString(R.string.default_regex_validation_message)
            addValidator(RegexValidator(regex, errorMessage ?: "Error Occurred"))
        }
    }

    private fun initializeTextWatcher() {
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (isAutoValidated)
                    validate()
                else
                    error = null
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun clearValidators() {
        validators?.clear()
        isErrorEnabled = false
    }

    fun addValidator(pValidator: BaseValidator) {
        validators?.add(pValidator)
    }

    fun autoValidate(flag: Boolean) {
        isAutoValidated = flag
    }

    fun autoTrimValue(flag: Boolean) {
        isAutoTrimEnabled = flag
    }

    fun validate(): Boolean {
        var status = true
        val text = value
        
        validators?.let {
            for (validator in it) {
                if (!validator.validate(text)) {
                    isErrorEnabled = true
                    error = validator.errorMessage
                    status = false
                    break
                } else {
                    error = null
                }
            }
        }
        
        if (status && !errorAlwaysEnabled) 
            isErrorEnabled = false
        
        return status
    }
}