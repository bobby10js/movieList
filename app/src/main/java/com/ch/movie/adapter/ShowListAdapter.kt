package com.ch.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ch.movie.R
import com.ch.movie.model.Movie
import com.ch.movie.model.TvShow

class ShowListAdapter(private var thumbNailActions: ThumbNailActions): RecyclerView.Adapter<ShowListAdapter.ViewHolder>() {
 
    private var showList: Array<Any> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.thumbnail_card, parent, false)
        val width = (parent.width * 0.34).toInt()
        val height = (width * 1.41).toInt()
        view.layoutParams = ViewGroup.LayoutParams(width, height)
        return ViewHolder(view)
    }


    fun setTvShowList(tvShowList: Array<TvShow>) {
        if (tvShowList.isNotEmpty()) {
            this.showList = tvShowList as Array<Any>
            notifyDataSetChanged()
        }
    }
    fun setMovieList(movieList: Array<Movie>) {
        if (movieList.isNotEmpty()) {
            this.showList = movieList as Array<Any>
            notifyDataSetChanged()
        }
    }

    fun setMovieList(movieList: List<Any>) {
            this.showList = movieList.toTypedArray()
            notifyDataSetChanged()
    }
    fun setTvShowList(movieList: List<Any>) {
        this.showList = movieList.toTypedArray()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when{
             showList[position] is Movie ->{
                 val movie: Movie = showList[position] as Movie
                 holder.textView.text = movie.title
                 Glide.with(holder.imageView.context).load("https://image.tmdb.org/t/p/w185" + movie.poster_path).into(holder.imageView)
                 holder.imageView.setOnClickListener {
                     thumbNailActions.onClick(movie.id)
                 }
             }
            showList[position] is TvShow ->{
                val tvShow: TvShow = showList[position] as TvShow
                holder.textView.text = tvShow.name
                Glide.with(holder.imageView.context).load("https://image.tmdb.org/t/p/w185" + tvShow.poster_path).into(holder.imageView)
                holder.imageView.setOnClickListener {
                    thumbNailActions.onClick(tvShow.id)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return showList.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
    interface ThumbNailActions {
        fun onClick(id: Int)

    }
}