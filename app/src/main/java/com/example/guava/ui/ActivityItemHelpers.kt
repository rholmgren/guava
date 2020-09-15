package com.example.guava.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun ActivityDetails(distance: String, pace: String, time: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Statistic(key = "Distance", value = distance)
        Statistic(key = "Pace", value = pace)
        Statistic(key = "Time", value = time)
    }
}

@Composable
fun Statistic(key: String, value: String) {
    Column {
        Text(key, fontSize = TextUnit.Sp(10))
        Text(value, fontSize = TextUnit.Sp(14))
    }
}


@Composable
fun ProfileRow(imageRes: Int, titleText: String, subtitleText: String) {
    Row {
        ProfilePic(imageRes = imageRes)
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        NameAndTime(
            titleText,
            subtitleText,
            modifier = Modifier.gravity(Alignment.CenterVertically)
        )
    }
}

@Composable
fun NameAndTime(name: String, displayTime: String, modifier: Modifier) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier) {
        Text(name, fontSize = TextUnit.Sp(14), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.preferredHeight(3.dp))
        Text(displayTime, fontSize = TextUnit.Sp(10))
    }
}

@Composable
fun ProfilePic(imageRes: Int) {
    val image = imageResource(id = imageRes)

    val imageModifier = Modifier
        .preferredHeight(48.dp)
        .preferredWidth(48.dp)
        .clip(shape = CircleShape)
    Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
}
