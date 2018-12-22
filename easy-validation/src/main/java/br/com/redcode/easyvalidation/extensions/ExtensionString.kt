package br.com.redcode.easyvalidation.extensions

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.Spanned

@SuppressLint("NewApi")
@Suppress("DEPRECATION")
fun String.toSpannedHTML(): Spanned {
    val isNougatOrNewer = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    return if (isNougatOrNewer) Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT) else Html.fromHtml(this)
}