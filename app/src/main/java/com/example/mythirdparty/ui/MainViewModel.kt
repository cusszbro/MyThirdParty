package com.example.mythirdparty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mythirdparty.data.remote.ApiConfig
import com.example.mythirdparty.data.remote.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _listUser = MutableLiveData<UserResponse>()
    val listUser : LiveData<UserResponse> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _lottieState = MutableLiveData<Boolean>()
    val lottieState : LiveData<Boolean> = _lottieState

    fun getListUser(query: String?) {
        _isLoading.value = true
        val retrofit = ApiConfig.getApiService()
        retrofit.getSearchUser(query).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _lottieState.value = false
                    _listUser.postValue(response.body())
                } else {
                    _lottieState.value = true
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                _lottieState.value = true
            }
        })
    }

}
