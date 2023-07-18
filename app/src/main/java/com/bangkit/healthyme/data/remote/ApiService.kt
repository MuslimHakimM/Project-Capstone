package com.bangkit.healthyme.data.remote

import com.bangkit.healthyme.data.model.auth.LoginRequest
import com.bangkit.healthyme.data.model.auth.LoginResponse
import com.bangkit.healthyme.data.model.auth.RegisterRequest
import com.bangkit.healthyme.data.model.auth.RegisterResponse
import com.bangkit.healthyme.data.model.food.ResponseMakanan
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("/register/")
    fun registerUser(
        @Body registerRequest: RegisterRequest
    ) : Call<RegisterResponse>

    @POST("/login/")
    fun loginUser(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @GET("/makanan")
    fun getAllMakanan(
        @Header("Authorization") auth : String
    ): Call<ResponseMakanan>

}