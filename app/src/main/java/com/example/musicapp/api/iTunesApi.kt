package com.example.jetpack_compose_sample_app.api

import com.example.musicapp.api.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesApi {
   @GET("search")
   suspend fun searchMusic(
      @Query("term") term: String,
      @Query("media") mediaType: String?,
      @Query("entity") entity: String?,
      @Query("limit") limit: Int?,
      @Query("country") country: String?,
   ): ApiResponse
}