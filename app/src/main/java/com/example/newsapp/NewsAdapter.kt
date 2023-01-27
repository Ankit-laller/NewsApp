package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listener :NewsItemClicked) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    private val newsList: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        val viewHolder = ViewHolder(itemView)
        itemView.setOnClickListener {
            listener.onItemClicked(newsList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = newsList[position]
        holder.NewsHeading.text =current.Heading
        holder.source.text = current.source
        Glide.with(holder.itemView.context).load(current.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        newsList.clear()
        newsList.addAll(updatedNews)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView :View):RecyclerView.ViewHolder(itemView) {
        var NewsHeading : TextView = itemView.findViewById(R.id.tvHeading)
        var source : TextView = itemView.findViewById(R.id.tvNews)
        var image :ImageView = itemView.findViewById(R.id.image)

    }

}
interface NewsItemClicked{
    fun onItemClicked(item:News)
}