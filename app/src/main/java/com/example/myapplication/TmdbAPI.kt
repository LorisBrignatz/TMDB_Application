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

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getMoviesInformations(
        @Path("movie_id") movieID: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : MoviesInfos

    @GET("tv/{tv_id}?append_to_response=credits")
    suspend fun getSeriesInformations(
        @Path("tv_id") serieID: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : SeriesInfos

    @GET("person/{person_id}?append_to_response=credits")
    suspend fun getActeursInformations(
        @Path("person_id") acteurID: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String) : ActeursInfos

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String) : TmdbMoviesResult

    @GET("search/tv")
    suspend fun getSearchSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String) : TmdbSeriesResult

    @GET("search/person")
    suspend fun getSearchActeurs(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String) : TmdbActorsResult
}

