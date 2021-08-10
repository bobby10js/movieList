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
import com.ch.movie.adapter.ThumbNailActions
import com.ch.movie.adapter.TvShowListAdapter
import com.ch.movie.api.Repository
import com.ch.movie.databinding.ActivityTvShowDetailedBinding
import com.ch.movie.model.Cast
import com.ch.movie.model.TvShow
import com.ch.movie.ui.movieDetailedView.TvShowDetailedViewModel


class TvShowDetailedActivity : AppCompatActivity() {

private lateinit var binding: ActivityTvShowDetailedBinding
    var id:Int = 0
    var tvShowList: ArrayList<TvShow> = ArrayList()
    var castList: ArrayList<Cast> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val similarActivityIntent = Intent(this, TvShowDetailedActivity::class.java)
        id = intent.getIntExtra("id",0)
        binding = ActivityTvShowDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val repository = Repository()
        val tvShowDetailedViewModel = TvShowDetailedViewModel(repository)
        tvShowDetailedViewModel.setDetail(id)
        tvShowDetailedViewModel.setSimilarTvShowDetail(id)
        tvShowDetailedViewModel.setCastDetails(id)

        val tvShowListAdapter = TvShowListAdapter(tvShowList, object : ThumbNailActions {
            override fun onClick(id: Int) {
                similarActivityIntent.putExtra("id", id)
                startActivity(similarActivityIntent)
            }
        })
        binding.similarTvShowsRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.similarTvShowsRecyclerView.adapter = tvShowListAdapter
        tvShowDetailedViewModel.getSimilarTvShowDetails().observe(this, { response ->
            tvShowListAdapter.pushToTvList(response.results)
        })

        tvShowDetailedViewModel.getTvShowDetail().observe(this, { response ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" +response.poster_path).into(binding.posterImageView)
            binding.title.text = response.name
            binding.date.text = "${(response.first_air_date.year+1900)} · ${response.genres?.joinToString {it.name }} · ${response.episode_run_time?.sum()} min"
            binding.ratingTextView.text = response.vote_average.toString()
            Log.i("response",response.first_air_date.toString()+"|"+response.vote_average/2)
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
        tvShowDetailedViewModel.getCastDetails().observe(this,{ response ->
                        castListAdapter.pushToCastList(response.cast)

            for (cast:Cast in response.cast) {

    Log.i("cast", cast.name)
}
        })




    }


}