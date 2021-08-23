package com.ch.movie.ui.tvShowList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.movie.R
import com.ch.movie.adapter.ShowListAdapter
import com.ch.movie.databinding.FragmentTvShowListBinding
import com.ch.movie.ui.tvShowDetailedView.TvShowDetailedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

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
        val tvShowListViewModel = ViewModelProvider(this).get(TvShowListViewModel::class.java)

        binding.tvShowListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = ShowListAdapter(object : ShowListAdapter.ThumbNailActions {
                override fun onClick(id: Int, viewType: Int) {
                    findNavController().navigate(R.id.navigation_tv_show_detailed, bundleOf("id" to id))
                }
            })

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        tvShowListViewModel.getTopRatedListNextPage()
                    }
                }
            })
        }

        tvShowListViewModel.getTopRatedListNextPage()
        tvShowListViewModel.getTvShowList().observe(viewLifecycleOwner, { response ->
            (binding.tvShowListRecyclerView.adapter as ShowListAdapter).setTvShowList(response)
        })

    }
}