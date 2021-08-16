package com.ch.movie.ui.watchLater

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentWatchLaterMovieListBinding
import com.ch.movie.ui.movieDetailedView.MovieDetailedActivity


class WatchLaterMovieListFragment : Fragment() {

    private  var _binding: FragmentWatchLaterMovieListBinding?=null
    private val binding get() = _binding!!

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
        binding.movieListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        val movieListAdapter =  ShowListAdapter( object : ShowListAdapter.ThumbNailActions {
            override fun onClick(id: Int) {
                val intent = Intent(activity, MovieDetailedActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        })
        binding.movieListRecyclerView.adapter = movieListAdapter
        watchLaterViewModel.getAllWatchList().observe(viewLifecycleOwner, { movieList ->
            Log.i("response", movieList.size.toString())
            movieListAdapter.setMovieList(movieList)
        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}