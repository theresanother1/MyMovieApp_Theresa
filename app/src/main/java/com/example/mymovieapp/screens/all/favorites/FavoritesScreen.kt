package com.example.mymovieapp.screens.all.favorites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovieapp.models.Movie
import com.example.mymovieapp.models.getFavorites
import com.example.mymovieapp.models.getMovies
import com.example.mymovieapp.navigation.MovieScreens
import com.example.mymovieapp.ui.theme.MyMovieAppTheme
import com.example.mymovieapp.viewmodel.FavoritesViewModel
import com.example.mymovieapp.widgets.MovieRow
import com.example.mymovieapp.widgets.favoriteButton


@Composable
fun FavoriteScreen (navController: NavController = rememberNavController(), viewModel: FavoritesViewModel){
    MyFavoritesApp (navController = navController /*added, else no navigation back*/){
        FavoritesContent(navController = navController, movieList = viewModel.favoriteMovies)
    }
}

@Composable
fun FavoritesContent(
    navController: NavController,
    movieList: List<Movie> = getFavorites()) {
    LazyColumn {
        items(movieList) { movie ->
            // movierow hat als argument eine funktion (navcontroller navigate bei click auf die row
            /*MovieRow(movie = movie){        //weil ich bei clickable movie.id angegeben habe, hab ich jetzt zugriff auf id der movierow
                    movieId ->
                navController.navigate(route= MovieScreens.DetailScreen.name +"/$movieId")
            }*/
            MovieRow(movie = movie, /*favoriteState = checkMovie*/ onItemClick = {movieId -> navController.navigate(route= MovieScreens.DetailScreen.name +"/$movieId")}){
                favoriteButton(isFavorite = true, movie = movie)
                //das ist das onItemClicked trailing lambda!!
                //   movieId -> navController.navigate(route= MovieScreens.DetailScreen.name +"/$movieId")
            }
        }
    }
}


@Composable
fun MyFavoritesApp(navController:NavController = rememberNavController(), content: @Composable () -> Unit) {

   // MyMovieAppTheme {
        //vergleichbar mit Surface, mit mehr Feature Möglichkeiten, zB Top Bar, floating Action buttons ....
        Scaffold(
            modifier = Modifier.background(color = Color.Cyan),
            topBar = {
                TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp){
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier.clickable {
                                Log.d("Favorites", "callback")
                                var x = navController.currentBackStackEntry
                                Log.d("Favorites", "In Favoritescreen before clicking backbutton $x")

                                navController.popBackStack()        //goes back to last screen
                                Log.d("Favorites", "In Favoritescreen AFTER clicking backbutton $x")

                            })
                        Spacer(modifier = Modifier.width(20.dp))

                        Column() {
                            Text(text="My Favorite Movies", style = MaterialTheme.typography.h4)
                        }
                    }
                }
            }
        )  {
            content()
            //HomeScreen()
        }
   // }
}
