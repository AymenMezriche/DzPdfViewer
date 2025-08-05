package com.app.dz.dzpdfviewer.util

import android.content.Context
import android.util.Log
import android.util.TypedValue
import com.app.dz.dzpdfviewer.R

object ThemeValidator {

    private var hasLoggedOnce = false

    fun validateThemeAttributes(context: Context, attrs: List<Int>) {
        if (hasLoggedOnce) return
        hasLoggedOnce = true

        val theme = context.theme
        attrs.forEach { attr ->
            val resolved = TypedValue()
            val success = theme.resolveAttribute(attr, resolved, true)
            if (!success) {
                Log.w("PdfViewerTheme", "⚠️ Missing theme attribute: ${context.resources.getResourceName(attr)}")
            }
        }
    }

    fun validatePdfViewerTheme(context: Context) {
        validateThemeAttributes(context, listOf(
            android.R.attr.colorBackground,
            R.attr.pdfView_toolbarColor,
            R.attr.pdfView_backgroundColor,
            R.attr.pdfView_titleTextStyle
        ))
    }
}
