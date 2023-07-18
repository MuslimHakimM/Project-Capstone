package com.bangkit.healthyme.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bangkit.healthyme.R
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        lifecycleScope.launch {
            delay(3500)
            if (true) {

                startActivity(Intent(this@ActivitySplash, ActivityStarted::class.java))
            } else {
                startActivity(Intent(this@ActivitySplash, ActivityMain::class.java))
                finish()
            }
        }
    }

        override fun onPause() {
            lifecycleScope.cancel()
            super.onPause()
        }
    }
