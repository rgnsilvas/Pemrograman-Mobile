package com.example.modul5.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
object ApiClient {
    private const val BASE_URL = "https://birthflowersapi.free.beeceptor.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val apiService: FlowerApiService = retrofit.create(FlowerApiService::class.java)
}
