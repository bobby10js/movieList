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
import com.ch.movie.ui.watchLater.WatchLaterViewModel

class MovieDetailedActivity : AppCompatActivity() {

private lateinit var binding: ActivityMovieDetailedBinding
    var id:Int = 0
    var movieList: ArrayList<Movie> = ArrayList()
    private var castList: ArrayList<Cast> = ArrayList()
    lateinit var movie:Movie
    var isAddedToWatchLater:Boolean? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val similarActivityIntent = Intent(this, MovieDetailedActivity::class.java)
        id = intent.getIntExtra("id",0)
        binding = ActivityMovieDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val watchListRepo = com.ch.movie.db.Repository(this)
        val repository = Repository()
        val movieDetailedViewModel = MovieDetailedViewModel(repository)
        val watchLaterViewModel = WatchLaterViewModel(watchListRepo)
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

        movieDetailedViewModel.getMovieDetails().observe(this, { movie ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" +movie.poster_path).into(binding.posterImageView)
            this.movie = movie
            binding.title.text = movie.title
            binding.date.text = "${(movie.release_date.year+1900)} · ${movie.genres?.joinToString {it.name }} · ${movie.runtime} min"
            binding.ratingTextView.text = movie.vote_average.toString()
            binding.overViewTxtView.text = movie.overview
            binding.ratingBar.rating= movie.vote_average/2
        })
        watchLaterViewModel.getAllWatchList().observe(this,{response ->
            for (movie in response)
                Log.i("watch Later",movie.title)

        })
        binding.fab.setOnClickListener { view ->
            if(isAddedToWatchLater==null){
                Snackbar.make(view, "Wait", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            }else if(isAddedToWatchLater as Boolean){
                watchLaterViewModel.removeFromWatchLater(movie)
                Snackbar.make(view, "Removed from Watch Later", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }else{
                watchLaterViewModel.addToWatchLater(movie)
                Snackbar.make(view, "Added to Watch Later", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }

        }
        watchLaterViewModel.getIsMovieAdded(id).observe(this,{isMovieAdded ->
            this.isAddedToWatchLater = isMovieAdded.isNotEmpty()
            if(this.isAddedToWatchLater as Boolean)
                binding.fab.setImageResource(R.drawable.remove)
            else
                binding.fab.setImageResource(R.drawable.watch_later)
            Log.i("watch Later", isMovieAdded.toString()+"!"+this.id)
        })

        val castListAdapter = CastListAdapter(castList)
        binding.castListRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.castListRecyclerView.adapter = castListAdapter
        movieDetailedViewModel.getCastDetails().observe(this,{ response ->
                        castListAdapter.pushToCastList(response.cast)

        })

    }


}