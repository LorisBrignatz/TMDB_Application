package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

/*@Composable
fun Films(viewModel: MainViewModel) {
    val mainState = viewModel.movies.collectAsState()

    when (val state = mainState.value) {
        is MainState.Loading -> {
        }
        is MainState.Success -> {
            val movies = state.movieList
        }
        is MainState.Error -> {
        }
    }
}
/*fun Films(viewModel: MainViewModel) {
    val movies by viewModel.state.collectAsState()

    if (movies.isEmpty()) {
        viewModel.getFilmsInitiaux()
    }
}*/