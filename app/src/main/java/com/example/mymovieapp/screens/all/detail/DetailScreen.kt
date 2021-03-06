package com.example.mymovieapp.screens.all.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
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
import com.example.mymovieapp.navigation.MovieScreens
import com.example.mymovieapp.viewmodel.FavoritesViewModel
import com.example.mymovieapp.widgets.HorizontalScrollableImageView
import com.example.mymovieapp.widgets.MovieRow
import com.example.mymovieapp.widgets.favoriteButton

//@Preview(showBackground = true)
@Composable
fun DetailScreen (
    viewModel: FavoritesViewModel,
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
            MainContent (movie = movie, viewModel = viewModel)
            //HomeScreen()
        }

}


@Composable
fun MainContent(movie: Movie, viewModel: FavoritesViewModel){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        Column {
            MovieRow(movie = movie) {
                var checkMovie: Boolean
                if (viewModel.favoriteMovies.isEmpty()) {
                    Log.d("main", "is empty")
                    checkMovie = false
                } else {
                    checkMovie = viewModel.checkIfFavorite(movie)
                    Log.d("main", "is not empty, ${movie.title} is $checkMovie")
                }
                /**added trailing lambda for actions if favorite Icon clicked
                 */
                favoriteButton(isFavorite = checkMovie, movie = movie, onIconClicked =
                { movieFavorite
                    ->
                    if (viewModel.favoriteMovies.isEmpty()) {
                        Log.d("main", "is empty")
                        checkMovie = false
                    } else {
                        checkMovie = viewModel.checkIfFavorite(movie)
                        Log.d("main", "is not empty, ${movieFavorite.title} is $checkMovie")
                    }
                    when (checkMovie) {
                        true ->
                            viewModel.removeFavorites(movieFavorite)
                        false ->
                            viewModel.addFavorites(movieFavorite)
                    }
                    Log.d("onIconClicked", "doing stuff with movie ${movieFavorite.title}")
                })

            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            HorizontalScrollableImageView(movie = movie)
        }
    }

    }



fun filterMovie(movieId: String?) : Movie{
    // movieId ist eindeutiger Wert, filtert durch und gibt ersten wert zur??ck
    return getMovies().filter { movie -> movie.id == movieId }[0]
}