package com.example.newsapp

import Model.NewsModel
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsapp.Fragments.BusinessFrag
import com.example.newsapp.Fragments.HomeFrag
import com.example.newsapp.Fragments.ScienceFrag
import com.example.newsapp.adapters.NewsItemClicked2
import com.example.newsapp.adapters.TabAdapter
import com.example.newsapp.adapters.businessAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_news_sections.*
import org.w3c.dom.Text

class NewsSections : AppCompatActivity() , NewsItemClicked2{

    private lateinit var adapter: businessAdapter
    private lateinit var newsArray:ArrayList<NewsModel>
    private lateinit var toggle :ActionBarDrawerToggle
    private  var nation:String? = null
    private lateinit var tabLayout :TabLayout
    private lateinit var viewPager :ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_sections)
        var token  = getSharedPreferences("nation", Context.MODE_PRIVATE)

        var nationChooser = findViewById<ImageButton>(R.id.nationChooser)
        nationChooser.setOnClickListener {
            val intent = Intent(this,Nations::class.java)
            token.edit().putString("nation"," ").commit()
            onResume()
            startActivity(intent)
        }

        var userName = intent.getStringExtra("userName")
        var user = findViewById<TextView>(R.id.user)
        user.setText(userName)


        var userIcon = findViewById<ImageButton>(R.id.profile)
        userIcon.setOnClickListener{

            fun onClick() {
                var navDrawer = findViewById<DrawerLayout>(R.id.drawerlayout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START)
                else navDrawer.closeDrawer(GravityCompat.END)
            }
            onClick()
        }


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
//
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
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home ->         Toast.makeText(this,"home",Toast.LENGTH_SHORT).show()
                R.id.business -> Toast.makeText(this,"home",Toast.LENGTH_SHORT).show()
                R.id.science -> Toast.makeText(this,"home",Toast.LENGTH_SHORT).show()
                R.id.nationChooser -> startActivity(Intent(this, Nations::class.java))

            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun sendData():String?{
        return nation
    }





    override fun onItemClicked(item: NewsModel) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

}