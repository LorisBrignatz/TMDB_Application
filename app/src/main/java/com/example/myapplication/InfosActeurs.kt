package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import java.util.regex.Pattern

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun InfosActeurs(navController: NavController, acteurId: String) {
    val customFont = Font(R.font.bebasneue)
    val infosActeurs: MainViewModel = viewModel()
    val acteursInfos by infosActeurs.acteursInfos.collectAsState()

    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
    }

    if (acteursInfos.name == "") {
        infosActeurs.ActeursInformations("fr-FR")
    }
    if (acteursInfos.name != "") {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally,
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/h632${acteursInfos.profile_path}"
                    ),
                    contentDescription = "Image acteur ${acteursInfos.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .height(20.dp)
                )
                Text(
                    text = acteursInfos.name,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(customFont),
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 15.dp, start = 16.dp, end = 16.dp)
            ) {
                val genderText = if (acteursInfos.gender == 1) "Femme" else "Homme"
                Text(
                    text = "$genderText -",
                    color = Color.Red,
                )

                if (acteursInfos.known_for_department != "") {
                    Text(
                        text = "${acteursInfos.known_for_department} -",
                        color = Color.Red,
                    )
                }

                val isBirthdayValid =
                    acteursInfos.birthday?.matches("^\\d{4}-\\d{2}-\\d{2}\$".toRegex()) == true
                if (acteursInfos.place_of_birth != "" && isBirthdayValid) {
                    Text(
                        text = "${acteursInfos.place_of_birth}",
                        fontStyle = FontStyle.Italic,
                        color = Color.Red,
                    )
                }
            }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 5.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)
                    ) {
                        Text(
                            text = "" + formatDate(
                                acteursInfos.birthday,
                                "yyyy-dd-MM",
                                "dd MMM yyyy",
                                Locale.FRANCE
                            ),
                            color = Color.Red
                        )
                }
            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = CenterHorizontally,
            ) {
                if (acteursInfos.biography != "") {
                    Text(
                        text = "Biographie",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(customFont),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, start = 15.dp)
                    )
                    Text(
                        text = acteursInfos.biography,
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }
        }
    }
}