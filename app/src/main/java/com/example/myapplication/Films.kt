package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class Films : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel: MainViewModel by viewModels()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
            }
        }
    }
}

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FilmsScreen(navController: NavController, windowClass: WindowSizeClass) {
        val mainViewModel: MainViewModel = viewModel()

        Scaffold(
            topBar = { TopNavBar(navController, windowClass) },
            bottomBar = { BottomNavBar(navController, windowClass, filmsBoolean = true) }
        ) {
                val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
                FilmsList(mainViewModel, navController, modifier = modifier)
            }
    }


    @Composable
    fun FilmsList(movieViewModel: MainViewModel, navController: NavController, nbColumns: Int = 2, modifier: Modifier) {
        val movies by movieViewModel.movies.collectAsState()

        if (movies.isEmpty()) {
            movieViewModel.MoviesOfTheWeek("fr-FR")
        }
        if (movies.isNotEmpty()) {
            LazyVerticalGrid(columns = GridCells.Fixed(nbColumns), modifier = modifier) {
                items(movies) { movie ->
                    MovieItem(movie = movie, navController = navController)
                }
            }
        }
    }

    @Composable
    fun MovieItem(movie: TmdbMovie, navController: NavController) {
        val imageUrl = "https://image.tmdb.org/t/p/w780${movie.poster_path}"

        FloatingActionButton(
            onClick = { navController.navigate("DetailMovie/${movie.id}") },
            modifier = Modifier.padding(20.dp),
            containerColor = Color.White,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                MovieImage(imageUrl = imageUrl)
                MovieDetails(title = movie.title, releaseDate = movie.release_date)
            }
        }
    }

    @Composable
    fun MovieImage(imageUrl: String) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)
                    size(350, 400)
                }
            ),
            contentDescription = "Image film",
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
        )
    }

    @Composable
    fun MovieDetails(title: String, releaseDate: String) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = releaseDate,
                color = Color.Black,
                modifier = Modifier.padding(top = 15.dp)
            )
        }
    }

