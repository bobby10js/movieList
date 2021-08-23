package com.ch.movie.ui.movieDetailedView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ch.movie.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ch.movie.adapter.CastListAdapter
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentMovieDetailedBinding
import com.ch.movie.model.Movie
import com.ch.movie.ui.watchLater.WatchLaterViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStoreOwner
import com.ch.movie.ui.movieList.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MovieDetailedFragment : Fragment() {
    private var movieId:Int = 0
    lateinit var movie: Movie
    private var isAddedToWatchLater:Boolean? =null

    private  var _binding: FragmentMovieDetailedBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt("id")?:0

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val watchLaterViewModel = ViewModelProvider(this).get(WatchLaterViewModel::class.java)
        val movieDetailedViewModel = ViewModelProvider(this).get(MovieDetailedViewModel::class.java)

        movieDetailedViewModel.setDetail(movieId)
        movieDetailedViewModel.setSimilarMovieDetail(movieId)
        movieDetailedViewModel.setCastDetails(movieId)

        binding.similarMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ShowListAdapter(object : ShowListAdapter.ThumbNailActions {
                override fun onClick(id: Int,viewType: Int) {
                    findNavController().navigate(R.id.navigation_movie_detailed, bundleOf("id" to id))
                }
            })
        }
        movieDetailedViewModel.getSimilarMovieDetails().observe(viewLifecycleOwner, { response ->
            (binding.similarMoviesRecyclerView.adapter as ShowListAdapter).setMovieList(response.results)
        })
        movieDetailedViewModel.getMovieDetails().observe(viewLifecycleOwner, { movie ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(binding.posterImageView)
            this.movie = movie
            binding.title.text = movie.title
            binding.date.text = "${(this.movie.release_date?.year?.plus(1900))} · ${this.movie.genres?.joinToString { it.name.toString() }} · ${this.movie.runtime} min"
            binding.ratingTextView.text = movie.vote_average.toString()
            binding.overViewTxtView.text = movie.overview
            binding.ratingBar.rating = movie.vote_average?.div(2)?:0f
        })

        binding.fab.setOnClickListener { view ->
            when {
                isAddedToWatchLater == null -> {
                    Snackbar.make(view, "Wait", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
                isAddedToWatchLater as Boolean -> {
                    watchLaterViewModel.removeFromWatchLater(movie)
                    Snackbar.make(view, "Removed from Watch Later", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
                else -> {
                    watchLaterViewModel.addToWatchLater(movie)
                    Snackbar.make(view, "Added to Watch Later", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            }
        }
        watchLaterViewModel.getIsMovieAdded(movieId).observe(viewLifecycleOwner,{ isMovieAdded ->
            this.isAddedToWatchLater = isMovieAdded
            if(this.isAddedToWatchLater as Boolean)
                binding.fab.setImageResource(R.drawable.remove)
            else
                binding.fab.setImageResource(R.drawable.watch_later)
        })

        val castListAdapter = CastListAdapter()
        binding.castListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.castListRecyclerView.adapter = castListAdapter
        movieDetailedViewModel.getCastDetails().observe(viewLifecycleOwner, { response ->
            castListAdapter.pushToCastList(response.cast)
        })
    }
}