package com.example.newsapp

import android.app.DownloadManager.Request
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.newsapp.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
lateinit var recyclerView: RecyclerView

class FirstFragment : Fragment(), newsItemClicked {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_View)
        recyclerView.layoutManager = layoutManager
        val items = fetchData()
        val adapter = NewsListAdapter(items,this)
        recyclerView.adapter = adapter

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    private fun fetchData(){
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=ed1c2752375543da9dc2d9cf85cd1895"
        val queue = Volley.newRequestQueue(context)

// Request a string response from the provided URL.

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response -> // Display the first 500 characters of the response string.

            }) {  }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: String) {
        Toast.makeText(context,"View Clicked is $item", Toast.LENGTH_SHORT).show()
    }
}
