package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.domain.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ImageOfTheDayApiService {
    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") key: String): PictureOfDay
}

object ImageApi {
    val imageOfTheDayApiService: ImageOfTheDayApiService by lazy {
        retrofit.create(
            ImageOfTheDayApiService::class.java
        )
    }
}