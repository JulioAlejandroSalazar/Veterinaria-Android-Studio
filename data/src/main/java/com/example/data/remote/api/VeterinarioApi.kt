package com.example.data.remote.api

import com.example.data.remote.dto.VeterinarioDto
import retrofit2.http.GET

interface VeterinarioApi {

    @GET("veterinarios")
    suspend fun getVeterinarios(): List<VeterinarioDto>
}
