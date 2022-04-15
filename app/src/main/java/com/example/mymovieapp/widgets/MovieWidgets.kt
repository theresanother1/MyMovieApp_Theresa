package com.example.mymovieapp.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mymovieapp.models.Movie
import com.example.mymovieapp.models.getMovies

import com.example.mymovieapp.viewmodel.FavoritesViewModel

@Preview
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[3],
            /*favoriteState:Boolean = false,*/
             //callback function --> zugriff auf die MovieId, die von unten nach oben gepoppt wird
             /**CALLBACK FUNCTION -> mit default value schreiben, sonst müsst ich parameter an JEDER EINZELNEN STELLE adaptieren */
             onItemClick: (String) -> Unit = {},    // kann jetzt jedes Mal, wenn MovieRow aufgerufen wird, dieses Lambda übergeben
             onIconClicked: (Movie) -> Unit = {},
             content: @Composable () -> Unit = {}
) {
    var changeArrow by remember{
        mutableStateOf(false)
    }

    /**
     * added for passing value
     */
    /*var favoriteState by remember{
        mutableStateOf(false)
    }*/

    Column() {
        Surface(
            //modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Card(
                modifier = Modifier
                    .padding(4.dp) //top = 10.dp, bottom = 40.dp (wie in CSS möglich)
                    .fillMaxWidth()
                    .heightIn(min = 130.dp)
                    .clickable {
                        onItemClick(movie.id)       //poppe die movieId zurück, weil oben callback definiert
                    },
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 6.dp
            ) {
                //vertical Alignment - Element wird in der Mitte angezeigt
                Row(verticalAlignment = Alignment.Top,modifier = Modifier.padding(5.dp)
                    /*.fillMaxWidth()*/) {
                    Surface(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(12.dp),
                        shape = CircleShape,
                        elevation = 6.dp
                    ) {
                        //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Picture.")
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                 .data(movie.images[0])
                                 .crossfade(true)
                                 .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape)
                        )

                    }

                    Column(
                        modifier = Modifier
                            .padding( 15.dp).width(200.dp)
                        //.height(110.dp)
                    ) {

                        Text(text = movie.title, style = MaterialTheme.typography.h5, fontStyle = FontStyle.Italic)
                        Text(text = movie.genre, style = MaterialTheme.typography.caption)
                        Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)

                        // change Arrow initialized with false

                        when (changeArrow) {
                            true -> Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Arrow Up",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clickable(onClick = { changeArrow = !changeArrow })
                            )
                            false -> Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Arrow Down",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clickable(onClick = { changeArrow = !changeArrow })
                            )

                        }
                        Column(modifier = Modifier.width(200.dp)) {
                            AnimatedVisibility(
                                visible = changeArrow,
                                enter = expandVertically(expandFrom = Alignment.Top)
                            ) {
                                Column() {
                                    Text(
                                        text = "Plot: ${movie.plot}",
                                        style = MaterialTheme.typography.caption
                                    )
                                    Divider(modifier = Modifier.padding(2.dp))
                                    Text(
                                        text = "Genre: ${movie.genre}",
                                        style = MaterialTheme.typography.caption
                                    )
                                    Text(
                                        text = "Actors: ${movie.actors}",
                                        style = MaterialTheme.typography.caption
                                    )
                                    Text(
                                        text = "Rating: ${movie.rating}",
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                            }
                        }
                    }
                    content()

                   /* Log.d("favorites", "favoriteState is $favoriteState, see ")
                    when (favoriteState) {
                        true ->
                            favoriteButton( isFavorite = favoriteState, movie = movie)


                           /* Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Arrow Up",
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable(onClick = { changeArrow = !changeArrow })
                                 )
                            */

                        false ->
                            favoriteButton( isFavorite = favoriteState, movie = movie)

                          /*  Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Arrow Down",
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable(onClick = { changeArrow = !changeArrow })
                        )

                           */

                    }*/

                    //favoriteButton( isFavorite = false, movie = movie)




                }

            }

        }
    }

    /**
     * added button as content of MovieRow in MainContent
     */

}


@Composable
fun favoriteButton(isFavorite:Boolean,
                   movie:Movie,
                   onIconClicked: (Movie) -> Unit = {}
){
    /*var state by remember{
        mutableStateOf(false)
    }*/
    var state = isFavorite
    // var state = isFavorite
    when (state) {
        true ->
            Column(modifier = Modifier.heightIn(min= 130.dp)
                , horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ){ IconButton(/*modifier = Modifier.clickable(onClick = { onItemClick(movie.id) } ), */onClick = {onIconClicked(movie); Log.d("favorites", "clicked from true -> $state")}) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", tint = Color.Cyan)
                //Log.d("favorites", "state is $state, should be false, bec true when started")
            }
            }
        false ->
            Column(modifier = Modifier.heightIn(min= 130.dp)
                , horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ){IconButton(/*modifier = Modifier.clickable(onClick = { onItemClick(movie.id) } ),*/ onClick = {onIconClicked(movie); Log.d("favorites", "clicked from false -> $state")}) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "NotFavorites", tint = Color.Cyan)
                //Log.d("favorites", "state is $state, should be true, bec false when started")
            }
            }

    }

}




/*@Composable
fun favoriteButton(isFavorite:Boolean,
                   movie:Movie,
                   onIconClicked: (Movie) -> Unit = {}
){
    var state by remember{
        mutableStateOf(false)
    }
    state = isFavorite
   // var state = isFavorite
    when (state) {
        true ->
        Column(modifier = Modifier.heightIn(min= 130.dp).fillMaxWidth().padding(horizontal = 2.dp)
            , horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ){ IconButton(/*modifier = Modifier.clickable(onClick = { onItemClick(movie.id) } ), */onClick = {onIconClicked(movie); Log.d("favorites", "clicked from true -> $state")}) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", tint = Color.Cyan)
            //Log.d("favorites", "state is $state, should be false, bec true when started")
        }
        }
        false ->
        Column(modifier = Modifier.heightIn(min= 130.dp).fillMaxWidth()
            , horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ){IconButton(/*modifier = Modifier.clickable(onClick = { onItemClick(movie.id) } ),*/ onClick = {onIconClicked(movie); Log.d("favorites", "clicked from false -> $state")}) {
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "NotFavorites", tint = Color.Cyan)
            //Log.d("favorites", "state is $state, should be true, bec false when started")
        }
    }

    }

}

 */





@Composable
fun HorizontalScrollableImageView(movie:Movie = getMovies()[0]){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = "Movie Images", style = MaterialTheme.typography.h5)
    }

    LazyRow{
        items(movie.images){ image ->

            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp),
                elevation = 6.dp){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "${movie.title} image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(corner = CornerSize(24.dp)))
                        .padding(6.dp)
                )
            }

        }
    }
}