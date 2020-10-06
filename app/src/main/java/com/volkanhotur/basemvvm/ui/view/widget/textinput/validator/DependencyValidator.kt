package com.volkanhotur.basemvvm.ui.view.widget.textinput.validator

import com.volkanhotur.basemvvm.ui.view.widget.textinput.ValidatedTextInputLayout

/**
 * @author volkanhotur
 */

class DependencyValidator(private val dependsOn: ValidatedTextInputLayout,
                          private val dependencyType: DependencyType,
                          errorMessage: String,
                          callback: ValidationCallback? = null) :
        BaseValidator(errorMessage, callback) {

    override fun isValid(text: String): Boolean {
        return when (dependencyType) {
            DependencyType.TYPE_EQUAL -> text == dependsOn.value
            DependencyType.TYPE_EQUAL_IGNORE_CASE -> text.equals(dependsOn.value, true)
            DependencyType.TYPE_REQUIRED_IF_EXISTS -> {
                if (dependsOn.value.isEmpty()) true else text.isNotEmpty()
            }
        }
    }

    companion object {
        enum class DependencyType {
            TYPE_EQUAL,
            TYPE_EQUAL_IGNORE_CASE,
            TYPE_REQUIRED_IF_EXISTS
        }
    }
}
