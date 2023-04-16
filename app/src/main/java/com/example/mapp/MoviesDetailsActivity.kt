package com.example.mapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mapp.data.MovieDetails
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetailsActivity : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var banner: ImageView

    lateinit var releaseDate: TextView
    lateinit var score: TextView
    lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        title = findViewById(R.id.movies_details_title)
        banner = findViewById(R.id.movies_details_image_banner)
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
        overview = findViewById(R.id.movies_details_body_overview)

        val id = intent.getIntExtra("id", 0)
        // Toast.makeText(this, "$id", Toast.LENGTH_LONG ).show()


        val apiInterface = ApiInterface.create().getMovieDetails(id, Constants.api_key)

        apiInterface?.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                title.text = response.body()?.title
                releaseDate.text = response.body()?.release_date.toString()
                score.text = response.body()?.vote_average.toString()
                overview.text = response.body()?.overview.toString()

                Picasso.get().load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path).into(banner)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {

                Toast.makeText(this@MoviesDetailsActivity, "Something get wrong ${t.message}", Toast.LENGTH_LONG).show()
            }

        }

        )


    }
}