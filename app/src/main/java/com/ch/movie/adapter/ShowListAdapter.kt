package com.ch.movie.adapter

import android.util.Log
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

    companion object{
        const val VIEW_MOVIE_TYPE = 1
        const val VIEW_TV_SHOW_TYPE = 2

    }
    private var showList: List<Any> = listOf()
    private var movieList: List<Movie> = listOf()
    private var tvShowList: List<TvShow> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.thumbnail_card, parent, false)
        val width = (parent.width * 0.34).toInt()
        val height = (width * 1.41).toInt()
        view.layoutParams = ViewGroup.LayoutParams(width, height)
        return ViewHolder(view)
    }


    fun setTvShowList(tvShowList: Array<TvShow>) {
        if (tvShowList.isNotEmpty()) {
            this.tvShowList = tvShowList.toList()
            setShowList()
        }
    }
    fun setMovieList(movieList: Array<Movie>) {
        if (movieList.isNotEmpty()) {
            this.movieList = movieList.toList()
            setShowList()
        }

    }

    fun setShowList(movieList: List<Movie>? = this.movieList, tvShowList: List<TvShow>? = this.tvShowList) {
        if(movieList!=null)
            this.movieList = movieList
        if(tvShowList!=null)
            this.tvShowList = tvShowList
        this.showList = this.movieList+this.tvShowList
        Log.i("ShowListAdapter",movieList?.size.toString()+"|"+tvShowList?.size+"|"+ this.showList.size.toString()+"|"+itemCount)

        notifyDataSetChanged()

    }


//    fun addToMovieList(movieList: List<Movie>) {
//        this.showList += movieList
//        notifyDataSetChanged()
//    }
//    fun addToTvShowList(tvShowList: List<TvShow>) {
//        this.showList += tvShowList
//        notifyDataSetChanged()
//    }
//

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(showList[position]){
              is Movie ->{
                 val movie: Movie = showList[position] as Movie
//                  Log.i("ShowListAdapter",movie.title+""+itemCount)

                  holder.textView.text = movie.title
                 Glide.with(holder.imageView.context).load("https://image.tmdb.org/t/p/w185" + movie.poster_path).into(holder.imageView)
                 holder.imageView.setOnClickListener {
                     thumbNailActions.onClick(movie.id, VIEW_MOVIE_TYPE)
                 }
             }
             is TvShow ->{
                val tvShow: TvShow = showList[position] as TvShow
//                 Log.i("ShowListAdapter",tvShow.name+""+itemCount)

                 holder.textView.text = tvShow.name
                Glide.with(holder.imageView.context).load("https://image.tmdb.org/t/p/w185" + tvShow.poster_path).into(holder.imageView)
                holder.imageView.setOnClickListener {
                    thumbNailActions.onClick(tvShow.id, VIEW_TV_SHOW_TYPE)
                }
            }
            else->{
//                Log.i("ShowListAdapter","error")
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
        fun onClick(id: Int, viewType: Int)

    }
}