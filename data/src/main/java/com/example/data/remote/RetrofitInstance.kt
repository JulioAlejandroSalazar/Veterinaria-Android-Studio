package com.example.data.remote

import com.example.data.remote.api.VeterinarioApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://tu-api.com/"

    val api: VeterinarioApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VeterinarioApi::class.java)
    }
}
