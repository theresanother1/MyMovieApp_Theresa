package com.example.mymovieapp
/*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyTopBar(content: @Composable () -> Unit){
    var showMenu by remember {
        mutableStateOf(false)
    }

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
}*/