package com.ch.movie.ui.tvShowList

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.movie.adapter.ThumbNailActions
import com.ch.movie.adapter.TvShowListAdapter
import com.ch.movie.api.Repository
import com.ch.movie.databinding.FragmentTvShowListBinding
import com.ch.movie.model.TvShow
import com.ch.movie.ui.tvShowDetailedView.TvShowDetailedActivity


class TvShowListFragment : Fragment() {

    private  var _binding: FragmentTvShowListBinding?=null
    private val binding get() = _binding!!

    private var tvShowList: ArrayList<TvShow> = ArrayList()
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
    fun getScreenWidth(context: Context?):Int {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, displayMetrics.widthPixels.toFloat(),context?.resources?.displayMetrics).toInt()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movieListAdapter =  TvShowListAdapter(tvShowList, object : ThumbNailActions {
            override fun onClick(id: Int) {
                val intent = Intent(activity, TvShowDetailedActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        })
        val repo = Repository()
        val viewModelFactory  = TvShowListViewModelFactory(repo)
        val viewModel: TvShowListViewModel = ViewModelProvider(this,viewModelFactory).get(TvShowListViewModel::class.java)
        Log.i("width",getScreenWidth(context).toString())
        binding.tvShowListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.tvShowListRecyclerView.adapter = movieListAdapter
        viewModel.getTopRatedList()

        viewModel.getTvShowList().observe(viewLifecycleOwner, { response ->
            Log.i("response",response.size.toString())
            movieListAdapter.pushToTvList(response)
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