package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.HandlerCompat.postDelayed
import com.example.newsapp.Fragments.HomeFrag
import com.example.newsapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_news_sections.*
import kotlinx.android.synthetic.main.items.view.*

class Nations : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private  lateinit var userName : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nations)
//        var token:SharedPreferences?  = getSharedPreferences("nation",Context.MODE_PRIVATE)
//
//        if(token?.getString("nation",null)?.isNotEmpty() == true){
//            startActivity(Intent(this,NewsSections::class.java))
//            finish()
//        }

        radioGroup = findViewById(R.id.radioGroup)
        var btn = findViewById<Button>(R.id.nationbtn)

        userName = findViewById(R.id.userName)


        btn.setOnClickListener {
            val selectBtn :Int? = radioGroup!!.checkedRadioButtonId
            val btn2 = findViewById<RadioButton?>(selectBtn!!)
            val name:String? =userName.text.toString()
            if(  btn2?.text.toString().isNotEmpty() ){
                val intent =Intent(this, NewsSections::class.java)

                Toast.makeText(this,btn2?.text.toString(), Toast.LENGTH_SHORT).show()
                intent.putExtra("userName", name!!)
                intent.putExtra("nation",btn2?.text.toString() )

//            var editor = token!!.edit()
//            editor!!.putString("nation",btn2.text.toString())
//            editor!!.commit()

                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this,"Enter name first",Toast.LENGTH_SHORT).show()
            }




        }

    }



}