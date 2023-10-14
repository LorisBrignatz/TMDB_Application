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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
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

class Series : ComponentActivity() {
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
fun SeriesScreen(navController: NavController, windowClass: WindowSizeClass) {
    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        topBar = { TopNavBar(navController, windowClass) },
        bottomBar = { BottomNavBar(navController, windowClass, seriesBoolean = true) }
    ) {
        val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
        SeriesList(mainViewModel, navController, modifier = modifier)
    }
}
@Composable
fun SeriesList(serieViewModel: MainViewModel, navController: NavController, nbColumns: Int = 2, modifier: Modifier) {
    val series by serieViewModel.series.collectAsState()

    if (series.isEmpty()) {
        serieViewModel.SeriesOfTheWeek("fr-FR")
    }
    if (series.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(nbColumns), modifier = modifier) {
            items(series) { serie ->
                SerieItem(serie = serie, navController = navController)
            }
        }
    }
}

@Composable
fun SerieItem(serie: TmdbSerie, navController: NavController) {
    val imageUrl = "https://image.tmdb.org/t/p/w780${serie.poster_path}"

    FloatingActionButton(
        onClick = { navController.navigate("DetailSerie/${serie.id}") },
        modifier = Modifier.padding(20.dp),
        containerColor = Color.White,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            SerieImage(imageUrl = imageUrl)
            SerieDetails(title = serie.original_name, releaseDate = serie.first_air_date)
        }
    }
}

@Composable
fun SerieImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                size(350, 400)
            }
        ),
        contentDescription = "Image série",
        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
    )
}

@Composable
fun SerieDetails(title: String, releaseDate: String) {
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