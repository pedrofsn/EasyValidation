package br.com.redcode.easyvalidation.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import br.com.redcode.easyform.library.R
import com.google.android.material.textfield.TextInputLayout

fun EditText.getDataAfterValidateInput(errorMessage: String = context.getString(R.string.this_field_is_not_filled), fieldName: String? = null): String? {
    val data: String? = getStringFromEditText()
    val hint: String? = when {
        fieldName != null && fieldName.isNotBlank() -> fieldName.toString()
        hint != null && hint.isNotBlank() -> hint.toString()
        parent is FrameLayout && parent.parent is TextInputLayout -> (parent.parent as TextInputLayout).hint.toString()
        else -> null
    }

    hideKeyboard()

    if ((data == null || data.isBlank()) && errorMessage.isNotBlank()) {
        setMessageError(String.format(errorMessage, hint))
        return null
    } else {
        error = null
    }

    return data
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
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.setMessageError(message: String, color: String = "red") {
    error = "<font color='$color'>$message</font>".toSpannedHTML()
}

fun EditText.getString(): String {
    hideKeyboard()
    return text.toString().trim()
}