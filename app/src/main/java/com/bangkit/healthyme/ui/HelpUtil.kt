package com.bangkit.healthyme.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

object HelpUtil {
    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun setStatusBarColor(activity: Activity, color: Int, view: View, state: Boolean = true) {
        activity.window.statusBarColor = ContextCompat.getColor(activity, color)
        WindowInsetsControllerCompat(activity.window, view).isAppearanceLightStatusBars = state
    }
}