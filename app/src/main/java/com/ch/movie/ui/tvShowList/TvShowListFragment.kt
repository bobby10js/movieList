package com.ch.movie.ui.tvShowList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.movie.R
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.api.Repository
import com.ch.movie.databinding.FragmentTvShowListBinding

class TvShowListFragment : Fragment() {

    private  var _binding: FragmentTvShowListBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val repo = Repository()
        val viewModelFactory  = TvShowListViewModelFactory(repo)
        val viewModel: TvShowListViewModel = ViewModelProvider(this,viewModelFactory).get(TvShowListViewModel::class.java)
        val tvShowListAdapter =  ShowListAdapter( object : ShowListAdapter.ThumbNailActions {
            override fun onClick(id: Int,viewType: Int) {
                findNavController().navigate(R.id.navigation_tv_show_detailed, bundleOf("id" to id))
            }
        })
        binding.tvShowListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.tvShowListRecyclerView.adapter = tvShowListAdapter
        viewModel.getTopRatedListNextPage()
        viewModel.getTvShowList().observe(viewLifecycleOwner, { response ->
            tvShowListAdapter.setTvShowList(response)
        })

        binding.tvShowListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getTopRatedListNextPage()
                }
            }
        })
    }
}