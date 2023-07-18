package com.bangkit.healthyme.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.healthyme.data.model.auth.LoginRequest
import com.bangkit.healthyme.data.model.auth.RegisterRequest
import com.bangkit.healthyme.ui.utils.RETROFIT_TAG
import com.bangkit.healthyme.data.model.auth.RegisterResponse
import com.bangkit.healthyme.data.remote.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun registerUser(name: String, email: String,password: String) {
        _isLoading.value = true
        var registerRequest = RegisterRequest(name, email, password)
        ApiConfig().getApiService().registerUser(registerRequest)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        Log.d(RETROFIT_TAG, response.body()?.msg.toString())
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.d(RETROFIT_TAG, t.message.toString())
                }

            })
    }
}