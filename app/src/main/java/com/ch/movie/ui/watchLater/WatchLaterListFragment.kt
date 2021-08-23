package com.ch.movie.ui.watchLater

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ch.movie.R
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentWatchLaterListBinding
import com.ch.movie.ui.tvShowDetailedView.TvShowDetailedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

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

        val watchLaterViewModel = ViewModelProvider(this).get(WatchLaterViewModel::class.java)

        binding.watchLaterListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = ShowListAdapter(object : ShowListAdapter.ThumbNailActions {
                override fun onClick(id: Int, viewType: Int) {
                    when (viewType) {
                        ShowListAdapter.VIEW_MOVIE_TYPE -> findNavController().navigate(
                            R.id.navigation_movie_detailed,
                            bundleOf("id" to id)
                        )
                        ShowListAdapter.VIEW_TV_SHOW_TYPE -> findNavController().navigate(
                            R.id.navigation_tv_show_detailed,
                            bundleOf("id" to id)
                        )
                    }
                }
            })
        }
        watchLaterViewModel.getAllMovieWatchList().observe(viewLifecycleOwner, { watchLaterMovieList ->
            (binding.watchLaterListRecyclerView.adapter as ShowListAdapter).setShowList(  watchLaterMovieList,null)
            Log.i("WatchLaterList:Movie",watchLaterMovieList.size.toString())
        })

        watchLaterViewModel.getAllTvShowWatchList().observe(viewLifecycleOwner, { watchLaterTvShowList ->
            (binding.watchLaterListRecyclerView.adapter as ShowListAdapter).setShowList(null, watchLaterTvShowList)
            Log.i("WatchLaterList:TV",watchLaterTvShowList.size.toString())
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}