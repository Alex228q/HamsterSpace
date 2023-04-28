package com.example.hamsterspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hamsterspace.ui.theme.HamsterSpaceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HamsterSpaceTheme {
                HamsterApp()
            }
        }
    }
}


@Composable
fun HamsterApp() {
    val viewModel = viewModel<MyViewModel>()
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)

    ) {
        ImageCard(
            image = viewModel.hamsters[viewModel.currentIndex].imageHamster,
            title = viewModel.hamsters[viewModel.currentIndex].title,
            description = viewModel.hamsters[viewModel.currentIndex].description
        )
        TwoButtons(
            next = {
                viewModel.currentIndex = (viewModel.currentIndex + 1) % viewModel.hamsters.size
            },
            prev = {
                if (viewModel.currentIndex > 0) viewModel.currentIndex-- else viewModel.currentIndex =
                    viewModel.hamsters.size - 1
            }
        )
    }
}


@Composable
fun ImageCard(
    @DrawableRes image: Int,
    title: String,
    description: String
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .padding(22.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = description,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun TwoButtons(
    next: () -> Unit,
    prev: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = prev,
            colors = ButtonDefaults.buttonColors(contentColor = Color.Green),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Prev image")
        }

        Button(
            onClick = next,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Next image", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HamsterSpaceTheme {
        HamsterApp()
    }
}