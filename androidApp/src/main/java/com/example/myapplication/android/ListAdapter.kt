package com.example.myapplication.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*val movie = moviesList[position]
        holder.title.text = movie.getTitle()
        holder.genre.text = movie.getGenre()
        holder.year.text = movie.getYear()*/
    }

    override fun getItemCount(): Int {
        return 10
    }

}