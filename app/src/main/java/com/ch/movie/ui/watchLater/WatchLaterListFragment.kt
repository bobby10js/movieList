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
import com.ch.movie.databinding.FragmentWatchLaterListBinding
import com.ch.movie.ui.movieDetailedView.MovieDetailedActivity
import com.ch.movie.ui.tvShowDetailedView.TvShowDetailedActivity


class WatchLaterListFragment : Fragment() {

    private  var _binding: FragmentWatchLaterListBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchLaterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val watchListRepo = com.ch.movie.db.Repository(requireContext())
        val watchLaterViewModel = WatchLaterViewModel(watchListRepo)
        binding.watchLaterListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        val showListAdapter =  ShowListAdapter( object : ShowListAdapter.ThumbNailActions {
            override fun onClick(id: Int,viewType: Int) {
                val intent = when(viewType){
                    ShowListAdapter.VIEW_MOVIE_TYPE -> Intent(activity, MovieDetailedActivity::class.java)
                    ShowListAdapter.VIEW_TV_SHOW_TYPE -> Intent(activity, TvShowDetailedActivity::class.java)
                    else -> null
                }
                intent?.putExtra("id",id)
                startActivity(intent)
            }
        })
        binding.watchLaterListRecyclerView.adapter = showListAdapter

        watchLaterViewModel.getAllMovieWatchList().observe(viewLifecycleOwner, { watchLaterMovieList ->
            showListAdapter.setShowList(  watchLaterMovieList,null)
            Log.i("WatchLaterList:Movie",watchLaterMovieList.size.toString())
        })

        watchLaterViewModel.getAllTvShowWatchList().observe(viewLifecycleOwner, { watchLaterTvShowList ->
            showListAdapter.setShowList(null, watchLaterTvShowList)
            Log.i("WatchLaterList:TV",watchLaterTvShowList.size.toString())
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}