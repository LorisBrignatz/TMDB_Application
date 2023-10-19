package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
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
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun InfosSeries(navController: NavController, serieID: String) {
    val infosSeries: MainViewModel = viewModel()
    val seriesInfos by infosSeries.seriesInfos.collectAsState()

    if (seriesInfos.name == "") {
        infosSeries.SeriesInformations("fr-FR")
    }
    if (seriesInfos.name != "") {
        LazyColumn() {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = seriesInfos.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    )
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + seriesInfos.backdrop_path,
                            builder = {
                                crossfade(true)
                                size(600, 600)
                            }),
                        contentDescription = "Image serie ${seriesInfos.name}",
                        Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .fillMaxWidth()
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
                            data = "https://image.tmdb.org/t/p/w1280" + seriesInfos.poster_path,
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }),
                        contentDescription = "Image film ${seriesInfos.name}",
                        Modifier.padding(start = 25.dp, end = 10.dp, top = 5.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                    ) {
                        Text(
                            text = formatDate(
                                seriesInfos.first_air_date,
                                "yyyy-dd-MM",
                                "dd MMM yyyy",
                                Locale.FRANCE
                            ),
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 15.dp),
                            fontSize = 15.sp
                        )

                        Text(
                            text = getGenres(seriesInfos.genres),
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
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
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = seriesInfos.overview,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }
            if(seriesInfos.credits.cast.isNotEmpty()) {
                item {
                    Text(
                        text = "Têtes d'affiches",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
                items(seriesInfos.credits.cast.take(10)) { cast ->
                    FloatingActionButton(
                        onClick = { navController.navigate("InfosActeurs/${cast.id}") },
                        modifier = Modifier.padding(20.dp),
                        containerColor = Color.White,) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = "https://image.tmdb.org/t/p/w780" + cast.profile_path,
                                        builder = {
                                            crossfade(true)
                                            size(
                                                350,
                                                400
                                            )
                                        }),
                                    contentDescription = "Image série ${cast.name}",
                                    Modifier.padding(start = 5.dp, end = 5.dp)
                                )
                                Text(
                                    text = cast.name,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                                Text(
                                    text = cast.character,
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 15.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}