package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NewsListAdapter(val item :ArrayList<String>, private val listener:newsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder =NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClick(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
class NewsViewHolder(itemView:View): ViewHolder(itemView){
        val titleView : TextView = itemView.findViewById(R.id.newsTitle)
}

interface newsItemClicked{
    fun onItemClick(item:String)
}