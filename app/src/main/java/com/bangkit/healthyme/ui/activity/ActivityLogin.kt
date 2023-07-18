package com.bangkit.healthyme.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.bangkit.healthyme.data.model.user.UserSession
import com.bangkit.healthyme.databinding.ActivityLoginBinding
import com.bangkit.healthyme.ui.LoginPreference
import com.bangkit.healthyme.ui.utils.SHARED_PREFERENCES
import com.bangkit.healthyme.ui.viewmodel.LoginViewModel

class ActivityLogin : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelViewModel: LoginViewModel
    private lateinit var pref: SharedPreferences
    private lateinit var userPref: LoginPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

        setupViewModel()
        setupPreference()
        action()

    }

    private fun setupViewModel() {
        viewModelViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewModelViewModel.isLoading.observe(this) { showLoading(it) }
        viewModelViewModel.toastMessage.observe(this) { toast(it) }
        //viewModelViewModel.loginApp.observe(this) {homeScreen()}
        viewModelViewModel.userSession.observe(this) { userSession ->
            saveSession(userSession)
        }
        viewModelViewModel.hasil.observe(this) { hasil ->
            startActivity(Intent(this, ActivityMain::class.java))
        }
    }


    private fun setupPreference() {
        pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        userPref = LoginPreference(this)
    }

    private fun action() {
        binding.btnMasuk.setOnClickListener {
            login()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

        }
        binding.btnDaftar.setOnClickListener {
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(
                intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@ActivityLogin as Activity)
                    .toBundle()
            )
        }
    }

    private fun login() {

//        val email = binding.tvEmail.text.toString()
        val email = binding.ceLogin.text.toString()
        Log.d("ActivityLogin", "email : $email")
//        val password = binding.tvPassword.text.toString()
        val password = binding.cpPassword.text.toString()
        Log.d("ActivityLogin", "password : $password")
        when {
            email.isEmpty() -> {
                binding.inputEmail.error = "Masukkan email"
            }

            password.isEmpty() -> {
                binding.inputPw.error = "Masukkan password"
            }

            else -> {
                viewModelViewModel.loginUser(
                    email, password
//                    binding.tvEmail.text.toString(),
//                    binding.tvPassword.text.toString()
                )
            }
        }
    }

    private fun saveSession(user: UserSession) {
        userPref.setUser(user)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}