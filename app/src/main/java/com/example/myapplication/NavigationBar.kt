package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController, windowClass: WindowSizeClass) {
    val customFont = Font(R.font.bebasneue)
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Red,
                titleContentColor = Color.Black
            ),
            title = {
                Text(
                    text = "TMDB",
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(customFont),
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("Profil") }) {
                    Icon(Icons.Filled.Home, "Profil")
                }
            },
            actions = {
                IconButton(onClick = { /* TODO: Ajoutez l'action de recherche ici */ }) {
                    Icon(Icons.Filled.Search, "Search")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    navController: NavController,
    windowClass: WindowSizeClass,
    filmsBoolean: Boolean = false,
    seriesBoolean: Boolean = false,
    acteursBoolean: Boolean = false
) {
    val iconSize = 35.dp
    val iconTint = if (filmsBoolean || seriesBoolean || acteursBoolean) Color.White else Color.Black

    BottomAppBar(
        containerColor = Color.Red,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavAction("FilmsScreen", R.drawable.video, "Films", navController, iconSize, iconTint)
                NavAction("SeriesScreen", R.drawable.television, "Séries", navController, iconSize, iconTint)
                NavAction("ActeursScreen", R.drawable.acteur, "Acteurs", navController, iconSize, iconTint)
            }
        },
    )
}

@Composable
fun NavAction(
    route: String,
    iconResId: Int,
    text: String,
    navController: NavController,
    iconSize: Dp,
    iconTint: Color
) {
    IconButton(
        onClick = { navController.navigate(route) },
        modifier = Modifier.size(80.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                tint = Color.Black,
                modifier = Modifier.size(iconSize)
            )
            Text(
                text = text,
                fontSize = 15.sp,
                color = Color.Black
            )
        }
    }
}



