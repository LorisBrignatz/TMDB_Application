package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun InfosSeries(navController: NavController, serieID: String) {
    val customFont = Font(R.font.bebasneue)
    val infosSeries: MainViewModel = viewModel()
    val seriesInfos by infosSeries.seriesInfos.collectAsState()
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
    }

    if (seriesInfos.name == "") {
        infosSeries.SeriesInformations("fr-FR")
    }
    if (seriesInfos.name != "") {
        LazyColumn() {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + seriesInfos.backdrop_path,
                            builder = {
                                crossfade(true)
                                size(700, 700)
                            }
                        ),
                        contentDescription = "Image serie ${seriesInfos.name}",
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
                            data = "https://image.tmdb.org/t/p/w1280" + seriesInfos.poster_path,
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }),
                        contentDescription = "Image film ${seriesInfos.name}",
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
                            text = seriesInfos.name,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(customFont),
                            fontSize = 25.sp,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                            color = Color.White
                        )
                        Text(
                            text = formatDate(
                                seriesInfos.first_air_date,
                                "yyyy-dd-MM",
                                "dd MMM yyyy",
                                Locale.FRANCE
                            ),
                            color = Color.Red,
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 15.sp
                        )

                        Text(
                            text = getGenres(seriesInfos.genres),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
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
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = seriesInfos.overview,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp),
                        color = Color.White
                    )
                }
            }

            if(seriesInfos.credits.cast.isNotEmpty()) {
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
                            items(seriesInfos.credits.cast.take(10)) { cast ->
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