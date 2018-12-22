package br.com.redcode.easyvalidation.extensions

import android.widget.Spinner
import android.widget.Toast
import br.com.redcode.easyform.library.R
import br.com.redcode.easyvalidation.isNullOrEmpty

/**
 * Created by pedrofsn on 31/10/2017.
 */

fun <T : Spinner> T.getDataAfterValidateInput(errorMessage: String = context.getString(R.string.o_campo_x_nao_foi_preenchido)): String? {
    val data: String? = if (selectedItemPosition == 0) null else getStringFromSpinner()

    if (isNullOrEmpty(data) && !isNullOrEmpty(context)) {
        val defaulValueSpinner = if (!isNullOrEmpty(selectedItem)) selectedItem.toString() else null

        if (isNullOrEmpty(defaulValueSpinner).not()) {
            Toast.makeText(context, String.format(errorMessage, defaulValueSpinner), Toast.LENGTH_SHORT).show()
        }
    }

    return data
}

fun <T : Spinner> T.getStringFromSpinner(): String {
    val case = !isNullOrEmpty(this) && !isNullOrEmpty(selectedItem) && !isNullOrEmpty(selectedItem.toString())
    return if (case) selectedItem.toString().trim { it <= ' ' } else ""
}