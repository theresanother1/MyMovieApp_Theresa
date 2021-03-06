package com.example.mymovieapp.screens.all

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovieapp.models.Movie
import com.example.mymovieapp.models.getMovies
import com.example.mymovieapp.navigation.MovieScreens
import com.example.mymovieapp.ui.theme.MyMovieAppTheme
import com.example.mymovieapp.viewmodel.FavoritesViewModel
import com.example.mymovieapp.widgets.MovieRow
import com.example.mymovieapp.widgets.favoriteButton

@Composable
fun HomeScreen (navController: NavController = rememberNavController(), viewModel: FavoritesViewModel){
    MyApp (navController = navController){
        MainContent(navController = navController, movieList = getMovies(), viewModel = viewModel)
    }
}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie>, viewModel:FavoritesViewModel) {
    LazyColumn {
        items(movieList) { movie ->
            var checkMovie:Boolean
            if (viewModel.favoriteMovies.isEmpty()){
                Log.d("main", "is empty")
                checkMovie = false
            } else {
                checkMovie = viewModel.checkIfFavorite(movie)
                Log.d("main", "is not empty, ${movie.title} is $checkMovie")
            }

            // movierow hat als argument eine funktion (navcontroller navigate bei click auf die row
            //weil ich bei clickable movie.id angegeben habe, hab ich jetzt zugriff auf id der movierow
            //das ist das onItemClicked trailing lambda!!
        //   movieId -> navController.navigate(route= MovieScreens.DetailScreen.name +"/$movieId")
            MovieRow(movie = movie, /*favoriteState = checkMovie*/ onItemClick = {movieId -> navController.navigate(route= MovieScreens.DetailScreen.name +"/$movieId")},



            ){
                /**added trailing lambda for actions if favorite Icon clicked
                 */
                    favoriteButton(isFavorite = checkMovie, movie = movie,  onIconClicked =
                        {movieFavorite
                            ->
                            if (viewModel.favoriteMovies.isEmpty()){
                                Log.d("main", "is empty")
                                checkMovie = false
                            } else {
                                checkMovie = viewModel.checkIfFavorite(movie)
                                Log.d("main", "is not empty, ${movieFavorite.title} is $checkMovie")
                            }
                             when (checkMovie){
                                 true ->
                                     viewModel.removeFavorites(movieFavorite)
                                 false ->
                                     viewModel.addFavorites(movieFavorite)
                             }
                            Log.d("onIconClicked", "doing stuff with movie ${movieFavorite.title}")
                    })

            }

        }
    }
}




@Composable
fun MyApp(navController:NavController = rememberNavController(), content: @Composable () -> Unit) {

    //mach Observable, um das DropDownMenu zu expanden, oder nicht
    var showMenu by remember {
        mutableStateOf(false)
    }
    MyMovieAppTheme() {
        //vergleichbar mit Surface, mit mehr Feature M??glichkeiten, zB Top Bar, floating Action buttons ....
        Scaffold(
            modifier = Modifier.background(color = Color.Cyan),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Movies") },
                    backgroundColor = Color.Cyan,
                    actions = {
                        //here can be done more
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false },) {
                            DropdownMenuItem(onClick = {

                                /**TEST*/
                                var x = navController.currentBackStackEntry
                                Log.d("Favorites", "In Homescreen before clicking Favorites$x")
                                navController.navigate(route= MovieScreens.FavoritesScreen.name)

                            }) {
                                Row() {
                                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(4.dp)
                                    )
                                    Text(text = "Favorites", modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp))
                                }
                            }
                        }
                    })
            }
        ) {
            content()
            //HomeScreen()
        }
    }
}


