package com.ch.movie.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.movie.R
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentMovieListBinding
import com.ch.movie.ui.movieDetailedView.MovieDetailedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MovieListFragment : Fragment() {

    private  var _binding: FragmentMovieListBinding?=null
    private val binding get() = _binding!!
    val movieListViewModel :MovieListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        binding.movieListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter =  ShowListAdapter( object : ShowListAdapter.ThumbNailActions {
                override fun onClick(id: Int,viewType: Int) {
                    findNavController().navigate(R.id.navigation_movie_detailed, bundleOf("id" to id))
                }
            })
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        movieListViewModel.getTopRatedListNextPage()
                    }
                }
            })
        }

        movieListViewModel.getTopRatedListNextPage()
        movieListViewModel.getMovieList().observe(viewLifecycleOwner, { response ->
            (binding.movieListRecyclerView.adapter as ShowListAdapter).setMovieList(response)
        })

    }
}