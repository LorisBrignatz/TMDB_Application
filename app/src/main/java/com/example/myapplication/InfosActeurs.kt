package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import java.util.regex.Pattern

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun InfosActeurs(navController: NavController, acteurId: String) {
    val infosActeurs: MainViewModel = viewModel()
    val acteursInfos by infosActeurs.acteursInfos.collectAsState()

    if (acteursInfos.name == "") {
        infosActeurs.ActeursInformations("fr-FR")
    }
    if (acteursInfos.name != "") {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            // Section : Nom et Image
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = acteursInfos.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .align(CenterHorizontally)
                )

                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/h632" + acteursInfos.profile_path,
                        builder = {
                            crossfade(true)
                            size(500, 500)
                        }),
                    contentDescription = "Image acteur ${acteursInfos.name}",
                    Modifier.padding(start = 15.dp, end = 15.dp)
                )
            }

            // Section : Détails (Sexe, Métier, Naissance, Anniversaire)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                val genderText = if (acteursInfos.gender == 1) "Femme" else "Homme"
                Text(
                    text = "Sexe : $genderText",
                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                )

                if (acteursInfos.known_for_department != "") {
                    Text(
                        text = "Métier : ${acteursInfos.known_for_department}",
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }

                val isBirthdayValid = acteursInfos.birthday?.matches("^\\d{4}-\\d{2}-\\d{2}\$".toRegex()) == true
                if (acteursInfos.place_of_birth != "" && isBirthdayValid) {
                    Text(
                        text = "Lieu de naissance : ${acteursInfos.place_of_birth}",
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )

                    Text(
                        text = "Anniversaire : " + formatDate(
                            acteursInfos.birthday,
                            "yyyy-dd-MM",
                            "dd MMM yyyy",
                            Locale.FRANCE
                        ),
                        modifier = Modifier.padding(top = 15.dp),
                        fontSize = 15.sp
                    )
                }
            }

            // Section : Biographie
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                if (acteursInfos.biography != "") {
                    Text(
                        text = "Biographie",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )

                    Text(
                        text = acteursInfos.biography,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }
        }
    }
}
