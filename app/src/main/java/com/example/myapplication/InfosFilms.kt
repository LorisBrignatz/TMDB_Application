package com.example.myapplication

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun InfosFilms(navController: NavController, movieId: String) {
    val infosMovies: MainViewModel = viewModel()
    val movies by infosMovies.moviesInfos.collectAsState()

    if (movies.isEmpty()) {
        infosMovies.MoviesInformations(movieId, "fr-FR")
    }

    if (movies.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                MovieHeader(movie = MoviesInfos())
            }

            item {
                MoviePosterAndInfo(movie = MoviesInfos())
            }

            item {
                MovieSynopsis(movie = MoviesInfos())
            }
        }
    }
}

@Composable
fun MovieHeader(movie: MoviesInfos) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w1280${movie.backdrop_path}",
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "Titre film ${movie.title}",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun MoviePosterAndInfo(movie: MoviesInfos) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w1280${movie.poster_path}",
                builder = {
                    crossfade(true)
                    size(200, 300)
                }
            ),
            contentDescription = "Image film ${movie.title}",
            modifier = Modifier.size(200.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = formatDate(
                    movie.release_date,
                    "yyyy-MM-dd",
                    "dd MMM yyyy",
                    Locale.FRANCE
                ),
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = getGenres(movie.genres),
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun MovieSynopsis(movie: MoviesInfos) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Synopsis",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = movie.overview,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun getGenres(genres: List<Genre>): String {
    return genres.joinToString(", ") { it.name }
}


fun formatDate(dateString: String, inputPattern: String, outputPattern: String, locale: Locale): String {
    try {
        val inputFormat = SimpleDateFormat(inputPattern, locale)
        val outputFormat = SimpleDateFormat(outputPattern, locale)
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}





