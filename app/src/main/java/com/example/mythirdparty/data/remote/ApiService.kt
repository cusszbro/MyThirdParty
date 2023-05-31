package com.example.mythirdparty.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String?
    ): Call<UserResponse>

}