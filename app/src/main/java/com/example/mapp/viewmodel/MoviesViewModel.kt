package com.example.mapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mapp.data.MovieDetails
import com.example.mapp.data.Movies
import com.example.mapp.data.Result
import com.example.mapp.model.repository.MoviesDBRepository
import com.example.mapp.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()

        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "OnResponse success ${call.toString()}")
                _movies.postValue(response?.body()?.results)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "OnFailure ${t?.message}")
            }
        })
    }

    fun getMoviesDetails(id: Int) {
        val response = mMoviesRepository.getMovieDetail(id)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                Log.d("testLogs", "OnResponse success ${call.toString()}")
                _movieDetails.postValue(response?.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("testLogs", "OnFailure ${t?.message}")
            }
        })
    }
}