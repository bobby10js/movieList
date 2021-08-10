package com.ch.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ch.movie.R
import com.ch.movie.model.TvShow

class TvShowListAdapter(private var tvShowList: ArrayList<TvShow>, private var  thumbNailActions: ThumbNailActions): RecyclerView.Adapter<TvShowListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.thumbnail_card, parent, false)
        val width = (parent.width * 0.34).toInt()
        val height = (width*1.41).toInt()
        view.layoutParams = ViewGroup.LayoutParams(width, height)
        return ViewHolder(view)
    }

    fun pushToTvList(tvShowList: Array<TvShow>) {
        for (tvShow in tvShowList) {
            this.tvShowList.add(tvShow)
        }
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow: TvShow = tvShowList[position]
        holder.textView.text = tvShow.name
        Glide.with(holder.imageView.context)
            .load("https://image.tmdb.org/t/p/w185" + tvShow.poster_path).into(holder.imageView)
        holder.imageView.setOnClickListener {
            thumbNailActions.onClick(tvShow.id)
        }
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)


    }
}