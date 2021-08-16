package com.ch.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ch.movie.R
import com.ch.movie.model.Cast

class CastListAdapter(): RecyclerView.Adapter<CastListAdapter.ViewHolder>() {
    private var castList: Array<Cast> = arrayOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.inflate_card_cast, parent, false)
        return ViewHolder(view)
    }


    fun pushToCastList(castList: Array<Cast>){
        if (castList.isNotEmpty()) {
            this.castList = castList
            notifyDataSetChanged()
        }

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast: Cast = castList[position]
        holder.textView.text = cast.name
        Glide.with(holder.imageView.context).load("https://image.tmdb.org/t/p/w185" +cast.profile_path).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        

    }

}