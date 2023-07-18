package com.bangkit.healthyme.ui

import android.content.Context
import com.bangkit.healthyme.ui.utils.ACCESSTOKEN
import com.bangkit.healthyme.ui.utils.LOGIN_STATE
import com.bangkit.healthyme.ui.utils.NAME
import com.bangkit.healthyme.ui.utils.PREFS_NAME
import com.bangkit.healthyme.ui.utils.USEREMAIL
import com.bangkit.healthyme.ui.utils.USER_ID
import com.bangkit.healthyme.data.model.user.UserSession

class LoginPreference (context: Context) {
    val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(user: UserSession) {
        val editor = preferences.edit()
        editor.putString(NAME, user.name)
        editor.putString(ACCESSTOKEN, user.accessToken)
        editor.putString(USER_ID, user.userId)
        editor.putString(USEREMAIL, user.userEmail)
        editor.putBoolean(LOGIN_STATE, user.isLogin)
        editor.apply()
    }

    fun logout() {
        val editor = preferences.edit()
        editor.remove(NAME)
        editor.remove(USER_ID)
        editor.remove(USEREMAIL)
        editor.remove(ACCESSTOKEN)
        editor.putBoolean(LOGIN_STATE, false)
        editor.apply()
    }

    fun getUser(): UserSession {
        return UserSession(
            preferences.getString(USER_ID, "") ?: "",
            preferences.getString(NAME, "")?: "",
            preferences.getString(USEREMAIL, "")?: "",
            preferences.getString(ACCESSTOKEN,"")?: "",
            preferences.getBoolean(LOGIN_STATE, false)
        )
    }
}