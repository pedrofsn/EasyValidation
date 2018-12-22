package br.com.redcode.easyvalidation

import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import br.com.redcode.easyvalidation.extensions.getDataAfterValidateInput
import java.util.*


/**
 * Created by pedrofsn on 27/10/2017.
 */
object Validate {

    fun getDataAsArray(vararg views: View): List<String> {
        var data = ArrayList<String>()
        val size = views.size

        if (!isNullOrEmpty(views)) {
            for (i in 0 until size) {
                val dataCollected = getData(views[i])

                dataCollected?.let {
                    if (!isNullOrEmpty(it)) {
                        data.add(it)
                    }
                }
            }
        }

        if (size != data.size) {
            data = ArrayList()
        }

        return data
    }

    fun isFilled(vararg arrayView: View): Boolean {
        var result = true
        if (!isNullOrEmpty(arrayView)) {
            for (i in arrayView.indices) {
                // Iterate over all views for show UI error if them not filled
                val dado = getData(arrayView[i])
                if (isNullOrEmpty(dado)) {
                    result = false
                }
            }
        }

        return result
    }

    private fun getData(view: View): String? = when (view) {
        is EditText -> view.getDataAfterValidateInput()
        is Spinner -> view.getDataAfterValidateInput()
        is TextView -> view.getDataAfterValidateInput()
        else -> null
    }

    infix fun check(x: View) = isFilled(x)

}