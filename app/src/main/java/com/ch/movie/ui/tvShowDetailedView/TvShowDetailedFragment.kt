package com.ch.movie.ui.tvShowDetailedView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ch.movie.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ch.movie.adapter.CastListAdapter
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentTvShowDetailedBinding
import com.ch.movie.model.TvShow
import com.ch.movie.ui.movieList.MovieListViewModel
import com.ch.movie.ui.watchLater.WatchLaterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class TvShowDetailedFragment : Fragment() {
    private var tvShowId:Int = 0
    lateinit var tvShow: TvShow
    private var isAddedToWatchLater:Boolean? =null
    private val watchLaterViewModel :WatchLaterViewModel by viewModels()
    private val tvShowDetailedViewModel :TvShowDetailedViewModel by viewModels()

    private  var _binding: FragmentTvShowDetailedBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTvShowDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvShowId = arguments?.getInt("id")?:0

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvShowDetailedViewModel.setDetail(tvShowId)
        tvShowDetailedViewModel.setSimilarTvShowDetail(tvShowId)
        tvShowDetailedViewModel.setCastDetails(tvShowId)

        binding.similarTvShowsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ShowListAdapter(object : ShowListAdapter.ThumbNailActions {
                override fun onClick(id: Int,viewType: Int) {
                    findNavController().navigate(R.id.navigation_tv_show_detailed, bundleOf("id" to id))
                }
            })

        }
        tvShowDetailedViewModel.getSimilarTvShowDetails()
            .observe(viewLifecycleOwner, { response ->
                (binding.similarTvShowsRecyclerView.adapter as ShowListAdapter).setTvShowList(response.results)
            })
        tvShowDetailedViewModel.getTvShowDetail().observe(viewLifecycleOwner, { tvShow ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvShow.poster_path).into(binding.posterImageView)
            this.tvShow = tvShow
            binding.title.text = tvShow.name
            binding.date.text = "${(tvShow.first_air_date?.year?.plus(1900))} · ${tvShow.genres?.joinToString { it.name.toString() }} · ${tvShow.episode_run_time?.sum()} min"
            binding.ratingTextView.text = tvShow.vote_average.toString()
            binding.overViewTxtView.text = tvShow.overview
            binding.ratingBar.rating = tvShow.vote_average?.div(2)?:0f
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
        watchLaterViewModel.getIsTvShowAdded(tvShowId).observe(viewLifecycleOwner,{ isTvShowAdded ->
            this.isAddedToWatchLater = isTvShowAdded
            if(this.isAddedToWatchLater as Boolean)
                binding.fab.setImageResource(R.drawable.remove)
            else
                binding.fab.setImageResource(R.drawable.watch_later)
        })

        val castListAdapter = CastListAdapter()
        binding.castListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.castListRecyclerView.adapter = castListAdapter
        tvShowDetailedViewModel.getCastDetails().observe(viewLifecycleOwner, { response ->
            castListAdapter.pushToCastList(response.cast)
        })
    }
}