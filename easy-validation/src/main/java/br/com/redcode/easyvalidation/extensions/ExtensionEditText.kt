package br.com.redcode.easyvalidation.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.StringRes
import br.com.redcode.easyform.library.R
import br.com.redcode.easyvalidation.Validate

fun EditText.getDataAfterValidateInput(
    errorMessage: String? = null,
    fieldName: String? = null
): String? {
    val data: String? = getStringFromEditText()
    val isEmptyData = data == null || data.isBlank()
    val hint: String? = getHint(fieldName)

    hideKeyboard()

    when {
        isEmptyData && errorMessage?.isBlank()?.not() == true -> {
            setMessageError(errorMessage)
            return null
        }

        isEmptyData && hint.isNullOrBlank().not() -> {
            val errorField = context.getString(R.string.field_x_is_not_filled, hint)
            setMessageError(errorField)
            return null
        }

        isEmptyData && hint.isNullOrBlank() -> {
            val errorGeneric = context.getString(R.string.this_field_is_not_filled)
            setMessageError(errorGeneric)
            return null
        }
        else -> error = null
    }

    return data
}

private fun EditText.getHint(fieldName: String?) = when {
    fieldName != null && fieldName.isNotBlank() -> fieldName.toString()
    hint != null && hint.isNotBlank() -> hint.toString()
    isWrappedByTextInputLayout() -> getTextInputLayout()?.hint?.toString()
    else -> null
}

private fun EditText.getStringFromEditText(): String {
    editableText?.toString()?.trim()?.apply {
        if (isNotBlank()) return this
    }

    return ""
}

fun EditText.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.hideKeyboard() {
    val systemService = context.getSystemService(Context.INPUT_METHOD_SERVICE)
    val imm = systemService as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.setMessageError(@StringRes intMessage: Int) {
    val message = context.getString(intMessage)
    setMessageError(message = message)
}

fun EditText.setMessageError(message: String?) {
    when (isWrappedByTextInputLayout()) {
        true -> getTextInputLayout()?.error = message
        false -> error = message
    }
}

fun EditText.getString(hideKeyboard: Boolean = true): String {
    if (hideKeyboard) {
        hideKeyboard()
    }
    return text.toString().trim()
}

fun EditText?.isFilled(fieldName: String? = null) = when {
    this == null -> false
    fieldName == null -> Validate.isFilled(this)
    else -> {
        val content = getDataAfterValidateInput(fieldName = fieldName)
        val result = content.isNullOrBlank().not()
        result
    }
}

infix fun EditText?.isFilledWithHint(@StringRes idString: Int): Boolean {
    return isFilledWithHint(this?.context?.getString(idString))
}

infix fun EditText?.isFilledWithHint(fieldName: String?) = isFilled(fieldName = fieldName)