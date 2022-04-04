package com.example.mymovieapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.models.Movie

//Achtung: ViewModel kann auch ohne gradle import verwendet werden, hat dann aber nicht alle funktionalitäten!!!
class FavoritesViewModel : ViewModel() {
    //mutable State List of weil:
    private var _movieList = mutableStateListOf<Movie>()
    val favoriteMovies : List<Movie>
        get() = _movieList

    //konstruktor von default werten (wenn gewünscht)
    init {
        _movieList.addAll(
            listOf(
                Movie(id = "tt0499549",
                    title = "Avatar",
                    year = "2009",
                    genre = "Action, Adventure, Fantasy",
                    director = "James Cameron",
                    actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
                    plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg",
                        "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
                    rating = "7.9"),

                Movie(id = "tt0416449",
                    title = "300",
                    year = "2006",
                    genre = "Action, Drama, Fantasy",
                    director = "Zack Snyder",
                    actors = "Gerard Butler, Lena Headey, Dominic West, David Wenham",
                    plot = "King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTMwNTg5MzMwMV5BMl5BanBnXkFtZTcwMzA2NTIyMw@@._V1_SX1777_CR0,0,1777,937_AL_.jpg",
                        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQwNTgyNTMzNF5BMl5BanBnXkFtZTcwNDA2NTIyMw@@._V1_SX1777_CR0,0,1777,935_AL_.jpg",
                        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTc0MjQzOTEwMV5BMl5BanBnXkFtZTcwMzE2NTIyMw@@._V1_SX1777_CR0,0,1777,947_AL_.jpg"
                    ),
                    rating = "7.7"),
            )
        )
    }

    fun addFavorites(movie: Movie) {
        _movieList.add(movie)
    }

    fun removeFavorites(movie: Movie) {
        _movieList.remove(movie)
    }

    fun getAllFavorites(): List<Movie> {
        return _movieList
    }

    fun checkIfFavorite(movie: Movie): Boolean {
        return _movieList.first() == movie
    }

}