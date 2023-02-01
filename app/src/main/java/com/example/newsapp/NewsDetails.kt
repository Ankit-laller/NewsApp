package com.example.newsapp

import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class NewsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        var imageUrl =intent.getStringExtra("image")
        var details = intent.getStringExtra("details")
        var content = intent.getStringExtra("content")
        var url = intent.getStringExtra("url")
        var newsDetails = findViewById<TextView>(R.id.newsDetails)
        newsDetails.setText(details+"\n"+content)
        var imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(baseContext).load(imageUrl).into(imageView)

        var browserSwitch = findViewById<TextView>(R.id.moreDetails)
        browserSwitch.setOnClickListener {
                    val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
        }
    }
}