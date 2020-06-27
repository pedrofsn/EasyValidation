package br.com.redcode.easyvalidation.extensions

import android.view.View
import android.widget.FrameLayout
import com.google.android.material.textfield.TextInputLayout

/*
    CREATED BY @PEDROFSN IN 27/06/20 11:32
*/

fun View.isWrappedByFrameLayout() = parent is FrameLayout
fun View.isWrappedByTextInputLayout() = parent.parent is TextInputLayout
fun View.getTextInputLayout() = parent.parent as? TextInputLayout