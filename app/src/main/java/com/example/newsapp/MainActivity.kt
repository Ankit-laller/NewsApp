package com.example.newsapp

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsArray:ArrayList<News>
    private lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsRecyclerView =findViewById(R.id.newsRecycler)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.hasFixedSize()
        newsArray = ArrayList()

        fetchData()
        adapter = NewsAdapter()
        newsRecyclerView.adapter = adapter

    }



    private fun fetchData() {
        val url ="https://newsdata.io/api/1/news?apikey=pub_16067e8ab26e081f558cd1cf429260ecf04b8&q=Indian%20IT%20sector "
        val url2 ="https://newsapi.org/v2/everything?q=tesla&from=2022-12-27&sortBy=publishedAt&apiKey=ed1c2752375543da9dc2d9cf85cd1895"
        val queue =Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,null,
            Response.Listener{
                val newsJSonArray =it.getJSONArray("results")
                Log.d("Test",it.toString())
                for(i in 0 until newsJSonArray.length()) {
                    val newsJsonObject = newsJSonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description")
                    )
                    newsArray.add(news)
                }
                adapter.updateNews(newsArray)
            },Response.ErrorListener {

            }
        )
//        jsonObjectRequest.setRetryPolicy(
//            DefaultRetryPolicy(
//                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//                2,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//        )
        queue.add(jsonObjectRequest)
    }



}