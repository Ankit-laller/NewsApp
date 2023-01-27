package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    private val newsList: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = newsList[position]
        holder.NewsHeading.text =current.Heading
        holder.NewsDetail.text = current.NewsDetail
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
        var NewsDetail : TextView = itemView.findViewById(R.id.tvNews)

    }

}