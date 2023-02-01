package com.example.newsapp.adapters

import Model.NewsModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R

class businessAdapter(private val listener : NewsItemClicked2) :
    RecyclerView.Adapter<businessAdapter.ViewHolder>(){
    private val newsList: ArrayList<NewsModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        val viewHolder = ViewHolder(itemView)
        itemView.setOnClickListener {
            listener.onItemClicked(newsList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = newsList[position]
        holder.NewsHeading.text =current.Heading
//        holder.source.text = current.source
        Glide.with(holder.itemView.context).load(current.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNews(updatedNews: ArrayList<NewsModel>){
        newsList.clear()
        newsList.addAll(updatedNews)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        var NewsHeading : TextView = itemView.findViewById(R.id.tvHeading)
        var image : ImageView = itemView.findViewById(R.id.image)

    }

}
interface NewsItemClicked2 {
    fun onItemClicked(item: NewsModel)
}
