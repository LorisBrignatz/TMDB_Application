package com.example.myapplication

import android.view.textclassifier.TextLanguage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val  movies = MutableStateFlow<List<Movie>>(listOf())

    val apikey ="b03d713739bb95e277d400986506278a"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun MoviesOfTheWeek(language: String) {
        viewModelScope.launch {
            movies.value = service.getMoviesOfTheWeek(apikey, language).results
        }
    }
}
