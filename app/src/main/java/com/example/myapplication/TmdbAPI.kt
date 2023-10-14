package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getMoviesOfTheWeek(
        @Query("api_key") apiKey: String,
        @Query("language") language: String): TmdbMoviesResult

    @GET("trending/tv/week")
    suspend fun getSeriesOfTheWeek(
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun getActorsOfTheWeek(
        @Query("api_key") api_key: String): TmdbActorsResult

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String) : TmdbMoviesResult

    /*@GET("trending/movie")
    suspend fun getFilmsInitiaux(
        @Query("api_key") apiKey: String,
        @Query("language") language: String): TmdbResult*/
}

