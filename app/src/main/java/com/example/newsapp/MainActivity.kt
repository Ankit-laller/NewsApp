package com.example.newsapp

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NewsItemClicked {

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
        adapter = NewsAdapter(this)
        newsRecyclerView.adapter = adapter

    }



    private fun fetchData() {
        val url ="https://newsdata.io/api/1/news?apikey=pub_16067e8ab26e081f558cd1cf429260ecf04b8&q=Internship&country=in&language=en&category=top "
        val url2 ="https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=ed1c2752375543da9dc2d9cf85cd1895"
        val queue =Volley.newRequestQueue(this)
        val jsonObjectRequest =object: JsonObjectRequest(
            Method.GET,
            url2,null,
            Response.Listener{
                val newsJSonArray =it.getJSONArray("articles")
                Log.d("Test",it.toString())
                for(i in 0 until newsJSonArray.length()) {
                    val newsJsonObject = newsJSonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("urlToImage"),
//                        newsJsonObject.getString("source_id"),
                        newsJsonObject.getString("url")
                    )
                    newsArray.add(news)
                }
                adapter.updateNews(newsArray)
            },Response.ErrorListener {}
        )
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                //headers.put("Content-Type", "application/json");
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
//        jsonObjectRequest.setRetryPolicy(
//            DefaultRetryPolicy(
//                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//                2,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//        )
        queue.add(jsonObjectRequest)
    }


    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

}