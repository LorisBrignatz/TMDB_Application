package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profil(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonImage()
        Spacer(modifier = Modifier.height(16.dp))
        Texte()
        Spacer(modifier = Modifier.height(100.dp))
        TextWithIcon()

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {navController.navigate("films")},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Démarrer")
        }
    }
}

@Composable
fun MonImage() {
    Column {
        Image(
            painterResource(R.drawable.loris),
            contentDescription = "Mon profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun Texte() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Brignatz Loris",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
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
