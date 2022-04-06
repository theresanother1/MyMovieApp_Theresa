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
        return _movieList.contains(movie)
    }

}