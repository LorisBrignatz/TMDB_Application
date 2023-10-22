package com.example.myapplication

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
    val customFont = Font(R.font.bebasneue)
    val genreBubbleShape = RoundedCornerShape(16.dp)
    val infosMovies: MainViewModel = viewModel()
    val moviesInfos by infosMovies.moviesInfos.collectAsState()

    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
    }

    if (moviesInfos.title.isEmpty()) {
        infosMovies.MoviesInformations("fr-FR")
    }

    if (moviesInfos.title.isNotEmpty()) {
        LazyColumn {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280${moviesInfos.backdrop_path}",
                            builder = {
                                crossfade(true)
                                size(700, 700)
                            }
                        ),
                        contentDescription = "Image film ${moviesInfos.title}",
                        Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280${moviesInfos.poster_path}",
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }
                        ),
                        contentDescription = "Image film ${moviesInfos.title}",
                        modifier = Modifier
                            .offset(y = (-30).dp)
                            .padding(start = 25.dp, end = 10.dp, top = 5.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                    ) {
                        Text(
                            text = moviesInfos.title,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(customFont),
                            fontSize = 25.sp,
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = Color.White
                        )
                        Text(
                            text = formatDate(
                                moviesInfos.release_date,
                                "yyyy-dd-MM",
                                "dd MMM yyyy",
                                Locale.FRANCE
                            ),
                            color = Color.Red,
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 15.sp
                        )
                        Text(
                            text = getGenres(moviesInfos.genres),
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            color = Color.White,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
            }
                item {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Synopsis",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(customFont),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                        Text(
                            text = moviesInfos.overview,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp),
                            color = Color.White
                        )
                    }
                }

                if (moviesInfos.credits.cast.isNotEmpty()) {
                    item {
                        Text(
                            text = "Casting",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(customFont),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 15.dp, start = 10.dp)
                        )
                    }
                    item {
                        LazyRow(
                        content = {
                            items(moviesInfos.credits.cast.take(10)) { cast ->
                                Box(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        FloatingActionButton(
                                            onClick = { navController.navigate("InfosActeurs/${cast.id}") },
                                            modifier = Modifier.size(120.dp).clip(CircleShape),
                                        ) {
                                            Image(
                                                painter = rememberImagePainter(
                                                    data = "https://image.tmdb.org/t/p/w780${cast.profile_path}",
                                                    builder = {
                                                        crossfade(true)
                                                        size(350, 400)
                                                    }
                                                ),
                                                contentDescription = "Image acteur ${cast.name}",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                        Text(
                                            text = cast.name,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    )
                    }
                }
            }
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





