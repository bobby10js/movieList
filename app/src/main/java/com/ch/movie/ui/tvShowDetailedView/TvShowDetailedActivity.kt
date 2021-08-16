package com.ch.movie.ui.tvShowDetailedView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ch.movie.R
import com.ch.movie.adapter.CastListAdapter
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.api.Repository
import com.ch.movie.databinding.ActivityTvShowDetailedBinding
import com.ch.movie.model.TvShow
import com.ch.movie.ui.watchLater.WatchLaterViewModel

class TvShowDetailedActivity : AppCompatActivity() {

private lateinit var binding: ActivityTvShowDetailedBinding
    var id:Int = 0
    lateinit var tvShow: TvShow
    var isAddedToWatchLater:Boolean? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val similarActivityIntent = Intent(this, TvShowDetailedActivity::class.java)
        id = intent.getIntExtra("id",0)
        binding = ActivityTvShowDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        val watchListRepo = com.ch.movie.db.Repository(this)
        val repository = Repository()
        val tvShowDetailedViewModel = TvShowDetailedViewModel(repository)
        val watchLaterViewModel = WatchLaterViewModel(watchListRepo)
        tvShowDetailedViewModel.setDetail(id)
        tvShowDetailedViewModel.setSimilarTvShowDetail(id)
        tvShowDetailedViewModel.setCastDetails(id)

        val tvShowListAdapter = ShowListAdapter(object : ShowListAdapter.ThumbNailActions {
            override fun onClick(id: Int,viewType: Int) {
                similarActivityIntent.putExtra("id", id)
                startActivity(similarActivityIntent)
            }
        })
        binding.similarTvShowsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.similarTvShowsRecyclerView.adapter = tvShowListAdapter
        tvShowDetailedViewModel.getSimilarTvShowDetails().observe(this, { response ->
            tvShowListAdapter.setTvShowList(response.results)
        })
        tvShowDetailedViewModel.getTvShowDetail().observe(this, { tvShow ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvShow.poster_path).into(binding.posterImageView)
            this.tvShow = tvShow
            binding.title.text = tvShow.name
            binding.date.text = "${(tvShow.first_air_date?.year?.plus(1900))} · ${tvShow.genres?.joinToString { it.name }} · ${tvShow.episode_run_time?.sum()} min"
            binding.ratingTextView.text = tvShow.vote_average.toString()
            binding.overViewTxtView.text = tvShow.overview
            binding.ratingBar.rating = tvShow.vote_average / 2
        })

        binding.fab.setOnClickListener { view ->

            when {
                isAddedToWatchLater == null -> {
                    Snackbar.make(view, "Wait", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
                isAddedToWatchLater as Boolean -> {
                    watchLaterViewModel.removeFromWatchLater(tvShow)
                    Snackbar.make(view, "Removed from Watch Later", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
                else -> {
                    watchLaterViewModel.addToWatchLater(tvShow)
                    Snackbar.make(view, "Added to Watch Later", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            }
        }
        watchLaterViewModel.getIsTvShowAdded(id).observe(this,{isTvShowAdded ->
            this.isAddedToWatchLater = isTvShowAdded
            if(this.isAddedToWatchLater as Boolean)
                binding.fab.setImageResource(R.drawable.remove)
            else
                binding.fab.setImageResource(R.drawable.watch_later)
        })

        val castListAdapter = CastListAdapter()
        binding.castListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.castListRecyclerView.adapter = castListAdapter
        tvShowDetailedViewModel.getCastDetails().observe(this, { response ->
            castListAdapter.pushToCastList(response.cast)
        })
    }
}