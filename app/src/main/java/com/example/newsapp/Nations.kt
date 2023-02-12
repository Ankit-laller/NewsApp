package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import com.example.newsapp.Fragments.HomeFrag
import com.example.newsapp.databinding.ActivityMainBinding

class Nations : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nations)

        radioGroup = findViewById(R.id.radioGroup)
        var btn = findViewById<Button>(R.id.nationbtn)

        btn.setOnClickListener {
            val intent =Intent(this, NewsSections::class.java)


            val selectBtn :Int = radioGroup!!.checkedRadioButtonId
            val btn2 = findViewById<RadioButton>(selectBtn)
            Toast.makeText(this,btn2.text.toString(), Toast.LENGTH_SHORT).show()

            intent.putExtra("nation", btn2.text.toString())
            startActivity(intent)
        }

    }



}