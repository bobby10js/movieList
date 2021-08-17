package com.ch.movie.ui.watchLater

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ch.movie.R
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentWatchLaterListBinding


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
                 when(viewType){
                    ShowListAdapter.VIEW_MOVIE_TYPE -> findNavController().navigate(R.id.navigation_movie_detailed, bundleOf("id" to id))
                    ShowListAdapter.VIEW_TV_SHOW_TYPE -> findNavController().navigate(R.id.navigation_tv_show_detailed, bundleOf("id" to id))
                }
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