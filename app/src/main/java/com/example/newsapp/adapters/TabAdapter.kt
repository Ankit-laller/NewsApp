package com.example.newsapp.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapp.Fragments.BusinessFrag
import com.example.newsapp.Fragments.HomeFrag
import com.example.newsapp.Fragments.ScienceFrag
import com.example.newsapp.Fragments.SportsFrag


internal class TabAdapter (var context:Context, fm :FragmentManager, var totalTabs :Int): FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                HomeFrag()
            }
            1->{
                BusinessFrag()
            }
            2->{
                ScienceFrag()
            }
            3->{
                SportsFrag()
            }
            else -> getItem(position)
        }
    }
}