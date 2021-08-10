package com.ch.movie.ui.movieDetailedView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ch.movie.R
import com.ch.movie.adapter.CastListAdapter
import com.ch.movie.adapter.MovieListAdapter
import com.ch.movie.adapter.ThumbNailActions
import com.ch.movie.api.Repository
import com.ch.movie.databinding.ActivityMovieDetailedBinding
import com.ch.movie.model.Cast
import com.ch.movie.model.Movie

class MovieDetailedActivity : AppCompatActivity() {

private lateinit var binding: ActivityMovieDetailedBinding
    var id:Int = 0
    var movieList: ArrayList<Movie> = ArrayList()
    var castList: ArrayList<Cast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val similarActivityIntent = Intent(this, MovieDetailedActivity::class.java)
        id = intent.getIntExtra("id",0)
        binding = ActivityMovieDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val repository = Repository()
        val movieDetailedViewModel = MovieDetailedViewModel(repository)
        movieDetailedViewModel.setDetail(id)
        movieDetailedViewModel.setSimilarMovieDetail(id)
        movieDetailedViewModel.setCastDetails(id)


        val movieListAdapter = MovieListAdapter(movieList, object : ThumbNailActions {
            override fun onClick(id: Int) {
                similarActivityIntent.putExtra("id", id)
                startActivity(similarActivityIntent)
            }
        })
        binding.similarMoviesRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.similarMoviesRecyclerView.adapter = movieListAdapter
        movieDetailedViewModel.getSimilarMovieDetails().observe(this, { response ->
            movieListAdapter.pushToMovieList(response.results)
        })

        movieDetailedViewModel.getMovieDetails().observe(this, { response ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" +response.poster_path).into(binding.posterImageView)
            binding.title.text = response.title
            binding.date.text = "${(response.release_date.year+1900)} · ${response.genres?.joinToString {it.name }} · ${response.runtime} min"
            binding.ratingTextView.text = response.vote_average.toString()
            Log.i("response",response.release_date.toString()+"|"+response.vote_average/2)
            binding.overViewTxtView.text = response.overview
            binding.ratingBar.rating= response.vote_average/2
        })

        val castListAdapter = CastListAdapter(castList, object : ThumbNailActions {
            override fun onClick(id: Int) {
                similarActivityIntent.putExtra("id", id)
                startActivity(similarActivityIntent)
            }
        })
        binding.castListRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.castListRecyclerView.adapter = castListAdapter
        movieDetailedViewModel.getCastDetails().observe(this,{ response ->
                        castListAdapter.pushToCastList(response.cast)

            for (cast:Cast in response.cast) {

    Log.i("cast", cast.name)
}
        })

    }


}