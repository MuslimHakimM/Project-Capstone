package com.bangkit.healthyme.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.healthyme.R
import com.bangkit.healthyme.databinding.ActivityDetailBinding
import com.bangkit.healthyme.ui.utils.FOOD
import com.bangkit.healthyme.ui.utils.FOOD2
import com.bangkit.healthyme.ui.utils.FOOD3
import com.bangkit.healthyme.ui.utils.FOOD4

class ActivityDetail : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Detail"
        setDetail()
    }

    private fun setDetail() {
        val food = intent.getStringExtra(FOOD)
        val food2 = intent.getStringExtra(FOOD2)
        val food3 = intent.getStringExtra(FOOD3)
        val food4 = intent.getStringExtra(FOOD4)

        binding.apply {

            ivStory.setImageResource(R.drawable.gambar4)

            tv1.text = food
            tv2.text = food2
            tv3.text = food3
            tv4.text = food4
        }
    }
}