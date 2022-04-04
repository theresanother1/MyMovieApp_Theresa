package com.example.mymovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymovieapp.screens.all.HomeScreen
import com.example.mymovieapp.screens.all.detail.DetailScreen
import com.example.mymovieapp.screens.all.favorites.FavoriteScreen
import com.example.mymovieapp.viewmodel.FavoritesViewModel

@Composable
fun MovieNavigation(){
    val navController = rememberNavController() //erzeugt über lib eine NavController Instanz mit Funktionen und Zeugs

    val viewModelFavorites: FavoritesViewModel = viewModel()


    NavHost(navController = navController, startDestination = "HomeScreen" /*hier startet die App*/){
        //Navigation Graph abstecken, Navigation kann grundsätzlich an jeder stelle gemacht werden, macht aber am meisten
        //Sinn in der Ebene, wo es sein soll --> Homescreen so weit oben wie möglich
        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController, viewModel = viewModelFavorites)} // --> verlinkung von String (enum.name) mit einzigartigen Composable
        composable(MovieScreens.FavoritesScreen.name) { FavoriteScreen(navController = navController, viewModel = viewModelFavorites)}

        //argumente werden wie in url verarbeitet, url: www.domain.com/detailscreen/movie=12
        composable(MovieScreens.DetailScreen.name +"/{movie}",
            //konkret wird eine ID übergeben
            //keine Objekte einfach übergeben, sondern so nur primitive Datentypen
            arguments=listOf(navArgument("movie") {
                type = NavType.StringType
            })
        //hol mir das Argument
        ) { backStackEntry ->
                   // hol mir das argument vom backstack, wenn das geht
             DetailScreen(navController=navController, movieId = backStackEntry.arguments?.getString("movie"), viewModel = viewModelFavorites)
        }

        //add more routes/Screens here

    }

}