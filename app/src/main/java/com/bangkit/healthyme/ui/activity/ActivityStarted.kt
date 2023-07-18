package com.bangkit.healthyme.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.healthyme.R
import com.bangkit.healthyme.data.model.user.UserSession
import com.bangkit.healthyme.ui.LoginPreference
import com.bangkit.healthyme.ui.utils.SHARED_PREFERENCES

class ActivityStarted : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private lateinit var userPref: LoginPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_started)
        supportActionBar?.hide()

        val btn1 = findViewById<Button>(R.id.btn_1)
        btn1.setOnClickListener {
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)

        }

        val btn2 = findViewById<TextView>(R.id.tv_login)
        btn2.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }

        setupPreference()
        saveSession()
    }

    private fun saveSession() {
        if (userPref.getUser().isLogin){

            val intent = Intent(this, ActivityMain::class.java)
            startActivity(intent)
        }
    }

    private fun setupPreference() {
        pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        userPref = LoginPreference(this)
    }

}