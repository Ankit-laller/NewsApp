package com.example.newsapp

import Model.NewsModel
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsapp.adapters.NewsItemClicked2
import com.example.newsapp.adapters.TabAdapter
import com.example.newsapp.adapters.businessAdapter
import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Text

class NewsSections : AppCompatActivity() , NewsItemClicked2{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: businessAdapter
    private lateinit var newsArray:ArrayList<NewsModel>

    private lateinit var tabLayout :TabLayout
    private lateinit var viewPager :ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_sections)


        tabLayout= findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Home"))
        tabLayout.addTab(tabLayout.newTab().setText("Business"))
        tabLayout.addTab(tabLayout.newTab().setText("Science"))
        tabLayout.addTab(tabLayout.newTab().setText("Sports"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val tabAdapter = TabAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = tabAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//        var nationchoose = findViewById<TextView>(R.id.nationChooser)
//        nationchoose.setOnClickListener {
//            startActivity(Intent(this,Nations::class.java))
//        }
//        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                viewPager.currentItem = tab!!.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
//            }
//
//        })

//        recyclerView = findViewById(R.id.business_newsRV)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.hasFixedSize()
//        newsArray = ArrayList()
//        fetchData_Bus()
//        adapter = businessAdapter(this)
//        recyclerView.adapter = adapter

    }

    private fun fetchData_Bus() {
        val url2 ="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=ed1c2752375543da9dc2d9cf85cd1895"
        val queue = Volley.newRequestQueue(this)
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
                        newsJsonObject.getString("url")
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
//        jsonObjectRequest.setRetryPolicy(
//            DefaultRetryPolicy(
//                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//                2,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//        )
        queue.add(jsonObjectRequest)
    }



    override fun onItemClicked(item: NewsModel) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

}