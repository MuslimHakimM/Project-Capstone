package com.bangkit.healthyme.ui.activity

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.healthyme.R
import com.bangkit.healthyme.databinding.ActivityAboutBinding
import com.bangkit.healthyme.ui.HelpUtil

class ActivityAbout : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            HelpUtil.setStatusBarColor(this@ActivityAbout, R.color.white, root)
            toolbar.setNavigationOnClickListener { onBackPressed() }
            tvDesc.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}