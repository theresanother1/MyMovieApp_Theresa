package com.example.mymovieapp.screens.all.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovieapp.models.Movie
import com.example.mymovieapp.models.getMovies
import com.example.mymovieapp.widgets.HorizontalScrollableImageView
import com.example.mymovieapp.widgets.MovieRow

@Preview(showBackground = true)
@Composable
fun DetailScreen (
    navController: NavController = rememberNavController(),
    movieId:String? = "tt0499549"    )
    {
        val movie = filterMovie(movieId = movieId)
        Scaffold(
            modifier = Modifier.background(color = Color.Cyan),
            topBar = {
                TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp){
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier.clickable {
                                navController.popBackStack()        //goes back to last screen
                            })
                        Spacer(modifier = Modifier.width(20.dp))

                        Column() {
                            Text(text=movie.title, style = MaterialTheme.typography.h4)
                        }
                    }
                }
            }
        ) {
            MainContent (movie = movie)
            //HomeScreen()
        }

}


@Composable
fun MainContent(movie: Movie){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){

        Column {
            MovieRow(movie=movie)
            Spacer(modifier=Modifier.height(8.dp))
            Divider()
            HorizontalScrollableImageView(movie = movie)
        }


    }

}

fun filterMovie(movieId: String?) : Movie{
    // movieId ist eindeutiger Wert, filtert durch und gibt ersten wert zurück
    return getMovies().filter { movie -> movie.id == movieId }[0]
}