package com.example.modul5.network

import com.example.modul5.model.FlowerRemote
import retrofit2.http.GET

interface FlowerApiService {
    @GET("flowers")
    suspend fun getFlowers(): List<FlowerRemote>
}