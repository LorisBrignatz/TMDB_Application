package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getMoviesOfTheWeek(
        @Query("api_key") apiKey: String,
        @Query("language") language: String): TmdbResult

    /*@GET("trending/movie")
    suspend fun getFilmsInitiaux(
        @Query("api_key") apiKey: String,
        @Query("language") language: String): TmdbResult*/
}

