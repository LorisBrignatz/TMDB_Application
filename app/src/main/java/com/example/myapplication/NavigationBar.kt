package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val customFont = Font(R.font.bebasneue)
    val currentDestination = navBackStackEntry?.destination
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var searchBarVisible by remember { mutableStateOf(false) }
    val imeAction = rememberUpdatedState(androidx.compose.ui.text.input.ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current

    if (!searchBarVisible) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Red,
                titleContentColor = Color.Black,
            ),
            title = {
                Text(
                    text = "TMDB",
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(customFont),
                    maxLines = 1,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow back",
                        tint = Color.Black,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            actions = {
                IconButton(onClick = { searchBarVisible = true }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                    )
                }
            },
        )
    }else{
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Effectuez l'action de recherche ici
                    if (currentDestination?.route == "FilmsScreen") {
                        mainViewModel.SearchMovies(searchText)
                    }
                    if (currentDestination?.route == "SeriesScreen") {
                        mainViewModel.SearchSeries(searchText)
                    }
                    if (currentDestination?.route == "ActeursScreen") {
                        mainViewModel.SearchActeurs(searchText)
                    }
                    searchActive = false
                    searchBarVisible = false
                }
            ),
            leadingIcon = {
                IconButton(onClick = { searchBarVisible = false }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { searchText = "" }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = Color.Black,
                    )
                }
            },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeftNavBar(
    navController: NavController,
    windowClass: WindowSizeClass,
    filmsBoolean: Boolean = false,
    seriesBoolean: Boolean = false,
    acteursBoolean: Boolean = false
) {
    val iconSize = 35.dp
    val iconTint = if (filmsBoolean || seriesBoolean || acteursBoolean) Color.White else Color.Black

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(56.dp)
            .background(Color.Red),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
                NavAction("FilmsScreen", R.drawable.video, "Films", navController, iconSize, iconTint)
                NavAction("SeriesScreen", R.drawable.television, "Séries", navController, iconSize, iconTint)
                NavAction("ActeursScreen", R.drawable.acteur, "Acteurs", navController, iconSize, iconTint)
            }
        }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopFloatNavBar(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val customFont = Font(R.font.bebasneue)
    val currentDestination = navBackStackEntry?.destination
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var searchBarVisible by remember { mutableStateOf(false) }
    val imeAction = rememberUpdatedState(androidx.compose.ui.text.input.ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!searchBarVisible) {
            FloatingActionButton(
                onClick = { searchBarVisible = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                )
            }
        } else {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Effectuez l'action de recherche ici
                        if (currentDestination?.route == "FilmsScreen") {
                            mainViewModel.SearchMovies(searchText)
                        }
                        if (currentDestination?.route == "SeriesScreen") {
                            mainViewModel.SearchSeries(searchText)
                        }
                        if (currentDestination?.route == "ActeursScreen") {
                            mainViewModel.SearchActeurs(searchText)
                    }
                        searchActive = false
                        searchBarVisible = false
                    }
                ),
                leadingIcon = {
                    IconButton(onClick = { searchBarVisible = false }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Black,
                        )
                    }
                },
            )
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopFloatNavBar(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val customFont = Font(R.font.bebasneue)
    val currentDestination = navBackStackEntry?.destination
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var searchBarVisible by remember { mutableStateOf(false) }
    val imeAction = rememberUpdatedState(androidx.compose.ui.text.input.ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current

    Box {
        if (!searchBarVisible) {
            FloatingActionButton(
                onClick = { searchBarVisible = true },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                )
            }
        } else {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Effectuez l'action de recherche ici
                        if (currentDestination?.route == "FilmsScreen") {
                            mainViewModel.SearchMovies(searchText)
                        }
                        if (currentDestination?.route == "SeriesScreen") {
                            mainViewModel.SearchSeries(searchText)
                        }
                        if (currentDestination?.route == "ActeursScreen") {
                            mainViewModel.SearchActeurs(searchText)
                        }
                        searchActive = false
                        searchBarVisible = false
                    }
                ),
                leadingIcon = {
                    IconButton(onClick = { searchBarVisible = false }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Black,
                        )
                    }
                },
            )
        }
    }
}*/





