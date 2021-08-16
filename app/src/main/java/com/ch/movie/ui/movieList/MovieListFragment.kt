package com.ch.movie.ui.movieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.api.Repository
import com.ch.movie.databinding.FragmentMovieListBinding
import com.ch.movie.ui.movieDetailedView.MovieDetailedActivity


class MovieListFragment : Fragment() {

    private  var _binding: FragmentMovieListBinding?=null
    private val binding get() = _binding!!

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
        val repo = Repository()
        val viewModelFactory  = MovieListViewModelFactory(repo)
        val viewModel: MovieListViewModel = ViewModelProvider(this,viewModelFactory).get(MovieListViewModel::class.java)
        val movieListAdapter =  ShowListAdapter( object : ShowListAdapter.ThumbNailActions {
            override fun onClick(id: Int) {
                val intent = Intent(activity, MovieDetailedActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        })
        binding.movieListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.movieListRecyclerView.adapter = movieListAdapter
        viewModel.getTopRatedListNextPage()
        viewModel.getMovieList().observe(viewLifecycleOwner, { response ->
            movieListAdapter.setMovieList(response)
        })
        binding.movieListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getTopRatedListNextPage()
                }
            }
        })
    }
}