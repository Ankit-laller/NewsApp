package com.example.newsapp.Fragments

import Model.NewsModel
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsapp.*
import com.example.newsapp.adapters.NewsItemClicked2
import com.example.newsapp.adapters.businessAdapter


class HomeFrag : Fragment() ,NewsItemClicked2{
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: businessAdapter
    private lateinit var newsArray:ArrayList<NewsModel>
    private lateinit var txt :TextView
    private lateinit var ProgessBar:ProgressBar


    var nation :String? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        ProgessBar = view.findViewById(R.id.homeProgess)
        recieveData()
        return view
    }

    private fun recieveData() {
        var d = (requireActivity() as NewsSections).sendData()
        nation =d
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = requireView().findViewById(R.id.home_newsRV)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.hasFixedSize()
        newsArray = ArrayList()
        ProgessBar.visibility = View.VISIBLE
        fetchData_Bus(context)
        ProgessBar.visibility = View.GONE
        adapter = businessAdapter(this)
        recyclerView.adapter = adapter

    }

    private fun fetchData_Bus(context: Context?) {
//        nation ="us"

        val url2 ="https://newsapi.org/v2/top-headlines?country=$nation&apiKey=ed1c2752375543da9dc2d9cf85cd1895"
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest =object: JsonObjectRequest(
            Method.GET,
            url2,null,
            Response.Listener{
                val newsJSonArray =it.getJSONArray("articles")
                Log.d("Test",it.toString())
                for(i in 0 until newsJSonArray.length()) {
                    val newsJsonObject = newsJSonArray.getJSONObject(i)
                    val news = NewsModel(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("content")
                    )
                    newsArray.add(news)
                }
                adapter.updateNews(newsArray)
            }, Response.ErrorListener {}
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
        jsonObjectRequest.setRetryPolicy(
            DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        )
        queue.add(jsonObjectRequest)
    }

    override fun onItemClicked(item: NewsModel) {

        val intent= Intent(context,NewsDetails::class.java)
        intent.putExtra("image",item.imageUrl)
        intent.putExtra("details", item.NewsDetail)
        intent.putExtra("content", item.content)
        intent.putExtra("url", item.url)
        startActivity(intent)
    }


}