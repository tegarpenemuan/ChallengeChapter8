package com.tegarpenemuan.challengechapter8.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.tegarpenemuan.challengechapter8.R
import com.tegarpenemuan.challengechapter8.data.api.tmdb.moviepopuler.MoviePopulerResponse
import com.tegarpenemuan.challengechapter8.datastore.pref
import com.tegarpenemuan.challengechapter8.ui.theme.ChallengeChapter8Theme
import com.tegarpenemuan.challengechapter8.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getMoviePopuler()
        val name = applicationContext.pref().getPrefNama()

        setContent {
            ChallengeChapter8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        HomeScreenApp(viewModel,name.toString())
                        DetailsContent(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreenApp(viewModel: MovieViewModel,nama:String) {
    val context = LocalContext.current
    val mContext = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    Column() {
        TopAppBar(
            title = {
                Text(
                    text = "Hai, $nama",
                    color = Color.Black
                )
            },
            backgroundColor = Color.LightGray,
            actions = {
                Button(
                    onClick = {
                        viewModel.logout()
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                        mContext.startActivity(Intent(mContext, LoginActivity::class.java))
                        activity!!.finish()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Logout", color = Color.White)
                }
            }

        )
    }
}

@Composable
fun EmployeeCard(movie: MoviePopulerResponse.ResultMoviePopuler) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("TITLE", movie.title)
                intent.putExtra("IMAGE", movie.poster_path)
                intent.putExtra("OVERVIEW", movie.overview)
                context.startActivity(intent)
                Toast
                    .makeText(
                        context,
                        "${movie.title}",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                val posterBaseUrl = "https://image.tmdb.org/t/p/w500/"
                Image(
                    painter = rememberAsyncImagePainter(posterBaseUrl + movie.poster_path),
                    contentDescription = null,
                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(140.dp)
                        .width(150.dp)
                        .height(150.dp)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = movie.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    text = "Description :- " + movie.overview,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                )
            }
        }
    }
}

@Composable
fun DetailsContent(viewModel: MovieViewModel) {
    val dataMovie by viewModel.dataMovieState.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(dataMovie) {
            EmployeeCard(movie = it)
        }
    }
}