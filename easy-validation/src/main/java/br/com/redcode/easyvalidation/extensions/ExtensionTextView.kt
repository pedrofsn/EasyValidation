package br.com.redcode.easyvalidation.extensions

import android.widget.TextView
import android.widget.Toast
import br.com.redcode.easyform.library.R
import br.com.redcode.easyvalidation.isNullOrEmpty

fun TextView.getDataAfterValidateInput(errorMessage: String = context.getString(R.string.field_x_is_not_filled)): String? {
    val fakeEditText = hint.isNullOrBlank().not()
    val data = if (text.isNullOrEmpty()) null else text.toString()
    val hint = if (fakeEditText) hint.toString() else null

    if (fakeEditText && isNullOrEmpty(data)) {
        Toast.makeText(context, String.format(errorMessage, hint), Toast.LENGTH_SHORT).show()
    }

    return data
}