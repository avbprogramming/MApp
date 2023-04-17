package com.example.mapp.model.repository

import com.example.mapp.Constants
import com.example.mapp.data.MovieDetails
import com.example.mapp.data.Movies
import com.example.mapp.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.api_key, "en-US", 1)
    }

    override fun getMovieDetail(id: Int): Call<MovieDetails> {
       return apiInterface.getMovieDetails(id, Constants.api_key)
    }


}