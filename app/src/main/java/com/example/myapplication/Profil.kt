package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.Font


@Composable
fun Profil(navController: NavController, windowClass: WindowSizeClass) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painterResource(id = R.drawable.fondecran),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MonImage()
            Spacer(modifier = Modifier.height(1.dp))
            /*Image(
                painter = painterResource(id = R.drawable.presentationnetflix),
                contentDescription = "Nom Netflix",
                modifier = Modifier
                    .size(300.dp)
            )*/
            Texte()
            Spacer(modifier = Modifier.height(50.dp))
            TextWithIcon()
            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = buttonColors(
                    containerColor =  Color.Black,
                    contentColor = Color.White
                ),
                onClick = { navController.navigate("FilmsScreen") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.Black, RoundedCornerShape(10.dp)),
                content = {
                    Text(
                        text = "Démarrer",
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            background = Color.Black
                        )
                                            )
                }
            )

        }
    }
}

@Composable
fun MonImage() {
    Column {
        val borderWidth = 5.dp
        Image(
            painter = painterResource(id = R.drawable.loris),
            contentDescription = "Mon profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .border(
                    BorderStroke(borderWidth, Color.Black),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        )
    }
}

@Composable
fun Texte() {
    val customFont = Font(R.font.bebasneue)
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Brignatz Loris",
            style = TextStyle(
                fontSize = 50.sp,
                fontFamily = FontFamily(customFont),
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        )
        Text(
            text = "Étudiant en 4ème année",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )
        Text(
            text = "École d'ingénieur ISIS",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
fun TextWithIcon() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.email),
            contentDescription = "Icon",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "lorisbrignatz@gmail.com",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.linkedin),
            contentDescription = "Icon",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "www.linkedin.com/in/loris-brignatz",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )
    }
}
