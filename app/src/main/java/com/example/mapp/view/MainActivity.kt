package com.example.mapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapp.*
import com.example.mapp.data.Result
import com.example.mapp.view.adapter.CustomAdapter
import com.example.mapp.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val mViewModel : MoviesViewModel = MoviesViewModel()

    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initObservers()
        mViewModel.getMovies()
    }

private fun initObservers() {
   mViewModel.apply {
       movies.observe(this@MainActivity){
           mMoviesAdapter = CustomAdapter(it,  this@MainActivity)
           mMoviesRecycler.adapter = mMoviesAdapter
       }
   }
}
    private fun initViews() {
        mMoviesRecycler = findViewById<RecyclerView>(R.id.recyclerview)
        mMoviesRecycler.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

override fun onItemClick(position: Int) {
    val intent = Intent(this@MainActivity, MoviesDetailsActivity::class.java)
    intent.putExtra("id", position)
    startActivity(intent)
}}
