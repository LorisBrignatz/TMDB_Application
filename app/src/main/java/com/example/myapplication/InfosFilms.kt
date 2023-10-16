package com.example.myapplication

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
fun InfosFilms(navController: NavController, movieID: String) {
    val infosMovies: MainViewModel = viewModel()
    val moviesInfos by infosMovies.moviesInfos.collectAsState()

    if (moviesInfos.title == "") {
        infosMovies.MoviesInformations("fr-FR")
    }
    if (moviesInfos.title != "") {
        LazyColumn() {
            // Titre + Image de fond du film
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = moviesInfos.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    )
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + moviesInfos.backdrop_path,
                            builder = {
                                crossfade(true)
                                size(600, 600)
                            }),
                        contentDescription = "Image film ${moviesInfos.title}",
                        Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .fillMaxWidth()
                    )
                }
            }
            // Affiche + Date de sortie + Genre
            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + moviesInfos.poster_path,
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }),
                        contentDescription = "Image film ${moviesInfos.title}",
                        Modifier.padding(start = 25.dp, end = 10.dp, top = 5.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                    ) {
                        Text(
                            text = formatDate(
                                moviesInfos.release_date,
                                "yyyy-dd-MM",
                                "dd MMM yyyy",
                                Locale.FRANCE
                            ),
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 15.dp),
                            fontSize = 15.sp
                        )

                        Text(
                            text = getGenres(moviesInfos.genres),
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                }
            }
            // Synopsis
            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "Synopsis",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = moviesInfos.overview,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }
        }
    }
}
/*@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
            items(movies) { movie ->
                MovieInfosItem(movie = movie, navController = navController)

                /* item {
                MoviePosterAndInfo(movie = MoviesInfos())
            }

            item {
                MovieSynopsis(movie = MoviesInfos())
            }*/
            }
        }
    }
}

@Composable
fun MovieInfosItem(movie: MoviesInfos, navController: NavController) {
    val imageFilmUrl = "https://image.tmdb.org/t/p/w1280${movie.backdrop_path}"
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        MovieHeader(title = movie.title, imageFilmUrl = imageFilmUrl)
        /*MovieDetails(title = movie.title, releaseDate = movie.release_date)*/
    }
}


@Composable
fun MovieHeader(title: String, imageFilmUrl : String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Image(
            painter = rememberImagePainter(
                data = imageFilmUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "Titre film",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(8.dp)
        )
    }
}

/*@Composable
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
    }*/
}*/
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





