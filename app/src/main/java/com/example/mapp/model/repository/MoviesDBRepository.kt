package com.example.mapp.model.repository

import com.example.mapp.data.MovieDetails
import com.example.mapp.data.Movies
import retrofit2.Call

interface MoviesDBRepository {
    fun getMovies() : Call<Movies>

    fun getMovieDetail(id: Int): Call<MovieDetails>
}