package com.example.mymovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovieapp.models.Movie
import com.example.mymovieapp.models.getMovies
import com.example.mymovieapp.navigation.MovieNavigation
import com.example.mymovieapp.screens.all.HomeScreen
import com.example.mymovieapp.ui.theme.MyMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*  MyMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(movieList = getMovies())
                }
            }
        }*/
            //MyApp {
                MovieNavigation()
                //HomeScreen()
                //MainContent(movieList = getMovies())
            //}
        }
    }

/*
    @Composable
    fun MyApp(content: @Composable () -> Unit) {

        //mach Observable, um das DropDownMenu zu expanden, oder nicht
        var showMenu by remember {
            mutableStateOf(false)
        }

        MyMovieAppTheme() {
           /* MyTopBar {
                content()
            }*/
            //vergleichbar mit Surface, mit mehr Feature Möglichkeiten, zB Top Bar, floating Action buttons ....
           Scaffold(
                modifier = Modifier.background(color = Color.Cyan),
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Movies") },
                        backgroundColor = Color.Cyan,
                        actions = {
                            //here can be done more
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More"
                                )
                            }

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false },
                                ) {
                                DropdownMenuItem(onClick = { /*TODO*/ }) {
                                    Row() {
                                        Icon(
                                            imageVector = Icons.Default.Favorite,
                                            contentDescription = "Favorites",
                                            modifier = Modifier.padding(4.dp)
                                        )
                                        Text(
                                            text = "Favorites",
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .width(100.dp)
                                        )
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
*/



/*
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApp {
            HomeScreen()
            //MainContent(movieList = getMovies())
            //MovieRow(name = "Harry Potter")
        }
    }*/
}