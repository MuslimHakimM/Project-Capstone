package com.bangkit.healthyme.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.healthyme.data.model.food.MakananItem
import com.bangkit.healthyme.data.model.food.ResponseMakanan
import com.bangkit.healthyme.data.remote.ApiConfig
import com.bangkit.healthyme.ui.utils.RETROFIT_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listStory = MutableLiveData<List<MakananItem>?>()
    val listMakanan: LiveData<List<MakananItem>?> = _listStory

    fun getMakanan(auth: String) {
        _isLoading.value = true
        ApiConfig().getApiService().getAllMakanan("Bearer $auth")
            .enqueue(object : Callback<ResponseMakanan> {
                override fun onResponse(
                    call: Call<ResponseMakanan>, response: Response<ResponseMakanan>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listStory.postValue(response.body()?.response?.makanan)
                    }

                }

                override fun onFailure(call: Call<ResponseMakanan>, t: Throwable) {
                    _isLoading.value = false
                    Log.d(RETROFIT_TAG, t.message.toString())
                }
            })




    }
}