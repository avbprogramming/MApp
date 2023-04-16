package com.example.mapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapp.data.ItemsViewModel
import com.example.mapp.data.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this, 2)

        val data = ArrayList<ItemsViewModel>()

        data.add(ItemsViewModel("https://api.themoviedb.org/3/movie/popular/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg"))


        val apiInterface = ApiInterface.create().getMovies(Constants.api_key)

        apiInterface.enqueue(object : Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                val adapter = CustomAdapter(response?.body()?.results, this)
                recyclerview.adapter = adapter

                Log.d("logsTEst", "onResponse Success ${response?.body()?.results}")

            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("logsTEst", "onResponse Failure ${t.toString()}")
            }

            override fun onItemClick(id: Int) {

                val intent = Intent(this@MainActivity, MoviesDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}