package com.ch.movie.ui.watchLater

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ch.movie.adapter.MovieListAdapter
import com.ch.movie.adapter.ThumbNailActions
import com.ch.movie.databinding.FragmentWatchLaterMovieListBinding
import com.ch.movie.model.Movie
import com.ch.movie.ui.movieDetailedView.MovieDetailedActivity
import com.ch.movie.ui.movieList.MovieListViewModel
import com.ch.movie.ui.movieList.MovieListViewModelFactory


class WatchLaterMovieListFragment : Fragment() {

    private  var _binding: FragmentWatchLaterMovieListBinding?=null
    private val binding get() = _binding!!

    var movieList: ArrayList<Movie> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchLaterMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val watchListRepo = com.ch.movie.db.Repository(requireContext())
        val watchLaterViewModel = WatchLaterViewModel(watchListRepo)

        val movieListAdapter =  MovieListAdapter(movieList , object : ThumbNailActions {
            override fun onClick(id: Int) {
                val intent = Intent(activity, MovieDetailedActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        })
        binding.movieListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.movieListRecyclerView.adapter = movieListAdapter

        watchLaterViewModel.setAllWatchLater()
        watchLaterViewModel.getAllWatchList().observe(viewLifecycleOwner, { response ->
            movieListAdapter.pushToMovieList(response)

        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}