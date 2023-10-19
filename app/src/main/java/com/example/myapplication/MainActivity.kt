package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val WindowSizeClass = calculateWindowSizeClass(this)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Screen(WindowSizeClass)
                      }
                 }
        }
                 }
        }
@Composable
fun Screen(windowclass : WindowSizeClass){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "profil") {
        composable("profil") {
            Profil(navController, windowclass)
        }
        composable("FilmsScreen") {
            FilmsScreen(navController, windowclass)
        }
        composable("SeriesScreen") {
            SeriesScreen(navController, windowclass)
        }
        composable("ActeursScreen") {
            ActeursScreen(navController, windowclass)
        }
        composable("InfosFilms/{movieID}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?: ""
            InfosFilms(navController, movieId)
        }
        composable("InfosSeries/{serieID}") { backStackEntry ->
            val serieId = backStackEntry.arguments?.getString("serieId")?: ""
            InfosSeries(navController, serieId)
        }
        composable("InfosActeurs/{acteurID}") { backStackEntry ->
            val acteurId = backStackEntry.arguments?.getString("acteurId")?: ""
            InfosActeurs(navController, acteurId)
        }


    }
}
/*@Composable
fun Greeting(name: String, viewModel: MainViewModel){
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        viewModel.MoviesOfTheWeek(language = "fr-FR")
    }

    LazyColumn{
        items(movies) { movie ->
            Text(text = movie.original_title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android", MainViewModel())
    }
}*/
