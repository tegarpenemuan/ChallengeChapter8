package com.tegarpenemuan.challengechapter8.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.tegarpenemuan.challengechapter8.R
import com.tegarpenemuan.challengechapter8.ui.theme.ChallengeChapter8Theme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        val title = intent.getStringExtra("TITLE")
                        val image = intent.getStringExtra("IMAGE")
                        val overview = intent.getStringExtra("OVERVIEW")
                        DetailScreenApp()
                        DisplayDetail(
                            title = title!!,
                            image = image!!,
                            overview = overview!!
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreenApp() {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    Column() {
        val appBarHorizontalPadding = 4.dp
        val titleIconModifier = Modifier
            .fillMaxHeight()
            .width(72.dp - appBarHorizontalPadding)

        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.height(32.dp)) {
                Row(titleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        IconButton(
                            onClick = {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                                activity?.finish()
                            },
                            enabled = true,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back",
                            )
                        }
                    }
                }

                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                text = "Movie Detail"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayDetail(image: String, title: String, overview: String) {
    val posterBaseUrl = "https://image.tmdb.org/t/p/w500/"
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberAsyncImagePainter(posterBaseUrl + image),
            contentDescription = null,
            modifier = Modifier
                .height(520.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 5.dp
                    )
                )
            )

            Text(
                text = "\nOverview: \n$overview",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(
                    PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                ),
                textAlign = TextAlign.Justify
            )
        }
    }
}


