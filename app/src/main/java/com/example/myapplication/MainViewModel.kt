package com.example.myapplication

import android.view.AbsSavedState
import android.view.textclassifier.TextLanguage
import androidx.lifecycle.SavedStateHandle
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

class MainViewModel( savedStateHandle: SavedStateHandle) : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbSerie>>(listOf())
    val acteurs = MutableStateFlow<List<TmdbActor>>(listOf())
    val moviesInfos = MutableStateFlow<MoviesInfos>(MoviesInfos())
    val seriesInfos = MutableStateFlow<SeriesInfos>(SeriesInfos())
    val acteursInfos = MutableStateFlow<ActeursInfos>(ActeursInfos())
    private val movieID: String? = savedStateHandle["movieID"]
    private val serieID: String? = savedStateHandle["serieID"]
    private val acteurID: String? = savedStateHandle["acteurID"]

    val apikey = "b03d713739bb95e277d400986506278a"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    /*val api = service.create(com.example.myapplication.TmdbAPI::class.java)*/

    fun MoviesOfTheWeek(language: String) {
        viewModelScope.launch {
            movies.value = service.getMoviesOfTheWeek(apikey, language).results
        }
    }

    fun SeriesOfTheWeek(language: String) {
        viewModelScope.launch {
            series.value = service.getSeriesOfTheWeek(apikey, language).results
        }
    }

    fun ActeursOfTheWeek() {
        viewModelScope.launch {
            acteurs.value = service.getActorsOfTheWeek(apikey).results
        }
    }

    fun MoviesInformations(language: String) {
        viewModelScope.launch {
            moviesInfos.value = service.getMoviesInformations(movieID?:"", apikey, language)
        }
    }

    fun SeriesInformations(language: String) {
        viewModelScope.launch {
            seriesInfos.value = service.getSeriesInformations(serieID?:"", apikey, language)
        }
    }

    fun ActeursInformations(language: String) {
        viewModelScope.launch {
            acteursInfos.value = service.getActeursInformations(acteurID?:"", apikey, language)
        }
    }
        fun SearchMovies(language: String, query: String) {
            viewModelScope.launch {
                movies.value = service.getSearchMovies(apikey, language, query).results
            }
        }
    }
