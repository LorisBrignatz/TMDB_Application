package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActeursScreen(navController: NavController, windowClass: WindowSizeClass) {
    val mainViewModel: MainViewModel = viewModel()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = { TopNavBar(navController) },
                bottomBar = { BottomNavBar(navController, windowClass, acteursBoolean = true) }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                ) {
                    val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
                    ActeursList(mainViewModel, navController, modifier = modifier)
                }
            }
        }

        else -> {
            Scaffold(
                bottomBar = {
                    LeftNavBar(navController, windowClass, acteursBoolean = true)
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                ) {
                    val modifier = Modifier.padding(start = 60.dp, end = 15.dp)
                    ActeursList(mainViewModel, navController, nbColumns = 4, modifier = modifier)
                }
            }
        }
    }
}


@Composable
fun ActeursList(actorViewModel: MainViewModel, navController: NavController, nbColumns: Int = 2, modifier: Modifier) {
    val acteurs by actorViewModel.acteurs.collectAsState()

    if (acteurs.isEmpty()) {
        actorViewModel.ActeursOfTheWeek()
    }
    if (acteurs.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(nbColumns), modifier = modifier) {
            items(acteurs) { actor ->
                ActorItem(actor = actor, navController = navController)
            }
        }
    }
}

@Composable
fun ActorItem(actor: TmdbActor, navController: NavController) {
    val imageUrl = "https://image.tmdb.org/t/p/w780${actor.profile_path}"

    FloatingActionButton(
        onClick = { navController.navigate("InfosActeurs/${actor.id}") },
        modifier = Modifier
            .padding(10.dp)
            .size(width = 500.dp, height = 300.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
        ) {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            crossfade(true)
                            size(500, 600)
                        }
                    ),
                    contentDescription = "Image acteur",
                )
                ActorDetails(title = actor.name)
            }
        }
    }
}

@Composable
fun ActorDetails(title: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}