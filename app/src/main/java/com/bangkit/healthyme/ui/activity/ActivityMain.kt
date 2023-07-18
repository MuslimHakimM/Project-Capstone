package com.bangkit.healthyme.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.healthyme.R
import com.bangkit.healthyme.data.model.user.UserSession
import com.bangkit.healthyme.ui.LoginPreference
import com.bangkit.healthyme.ui.utils.SHARED_PREFERENCES

class ActivityMain : AppCompatActivity() {

    private lateinit var userPref: LoginPreference
    private lateinit var pref: SharedPreferences
    private lateinit var Nama : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnlogout = findViewById<ImageButton>(R.id.btn_logout)
        btnlogout.setOnClickListener {
            logoutApp()

        }


        val btn1 = findViewById<LinearLayout>(R.id.linearLayout)
        btn1.setOnClickListener {
            val intent = Intent(this, ActivitySearch::class.java)
            startActivity(intent)

        }

        val btn2 = findViewById<LinearLayout>(R.id.linearLayout2)
        btn2.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        val btn3 = findViewById<LinearLayout>(R.id.linearLayout3)
        btn3.setOnClickListener {
            val intent = Intent(this, ActivityAbout::class.java)
            startActivity(intent)
        }
        setupPreference()
        saveSession()
    }

    private fun saveSession() {
        Nama=userPref.getUser().name
        val text = findViewById<TextView>(R.id.apk)
        text.text = Nama
    }

    private fun setupPreference() {
        pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        userPref = LoginPreference(this)
    }

    private fun Logout() {
        userPref.logout()
        val intent = Intent(this, ActivityStarted::class.java)
        startActivity(intent)
        finish()
    }

    private fun logoutApp() {

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah ingin Keluar?")
            builder.setCancelable(false)
            builder.setPositiveButton("Ya") { dialog, which ->
                Logout()
            }
            builder.setNegativeButton("Tidak") { dialog, which ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
