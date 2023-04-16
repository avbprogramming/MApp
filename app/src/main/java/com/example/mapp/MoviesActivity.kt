package com.example.mapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this, 2)

        val data = ArrayList<ItemsViewModel>()


        data.add(ItemsViewModel("https://api.themoviedb.org/3/movie/popular/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg"))


        val apiInterface = ApiInterface.create().getMovies(Constants.api_key)

        apiInterface.enqueue( object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                val adapter = CustomAdapter(response?.body()?.results)
                recyclerview.adapter = adapter

                Log.d("logsTEst", "onResponse Success ${response?.body()?.results}")

//                if(response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("logsTEst", "onResponse Failure ${t.toString()}")
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}