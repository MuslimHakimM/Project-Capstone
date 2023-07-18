package com.bangkit.healthyme.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.healthyme.data.model.auth.LoginRequest
import com.bangkit.healthyme.data.model.auth.LoginResponse
import com.bangkit.healthyme.data.model.user.UserSession
import com.bangkit.healthyme.data.remote.ApiConfig
import com.bangkit.healthyme.ui.utils.RETROFIT_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _token = MutableLiveData<String?>()
    val token: MutableLiveData<String?> = _token

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _userSession = MutableLiveData<UserSession>()
    val userSession: LiveData<UserSession> = _userSession

    private val _hasil = MutableLiveData<Boolean>()
    val hasil: LiveData<Boolean> = _hasil

    fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        ApiConfig().getApiService().loginUser(loginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _hasil.value = true
                        val responseBody = response.body()
                        _userSession.value = responseBody?.let {
                            UserSession(
                                userId = it.userId,
                                name = responseBody.name,
                                userEmail = responseBody.userEmail,
                                accessToken = responseBody.accessToken,
                                isLogin = true
                            )
                        }
                        val token = response.body()?.accessToken
                        if (token != null) {
                            Log.d("token", token)
                            _token.value = token
                        }
                    }

                    if (!response.isSuccessful) {
                        _toastMessage.value = response.message()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _toastMessage.value = t.message
                    _isLoading.value = false
                    Log.d(RETROFIT_TAG, t.message.toString())
                }

            })
    }
}
