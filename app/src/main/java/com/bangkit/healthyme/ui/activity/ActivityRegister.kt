package com.bangkit.healthyme.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.bangkit.healthyme.databinding.ActivityRegisterBinding
import com.bangkit.healthyme.ui.viewmodel.RegisterViewModel

class ActivityRegister : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Register"

        setupViewModel()
        action()
    }

    private fun action() {
        binding.btnDaftar.setOnClickListener {
            register()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewModel.isLoading.observe(this) { showLoading(it) }
    }


    private fun register() {
        val name = binding.tvNama.text.toString().trim()
        val email = binding.tvEmail.text.toString().trim()
        val password = binding.tvPassword.text.toString().trim()
        viewModel.registerUser(name, email, password)

        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this@ActivityRegister as Activity).toBundle())
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}