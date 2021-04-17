package com.mfahmi.myjetpackprosubmission.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.ui.fragments.TvShowFragment

class MainPagerAdapter(activity: AppCompatActivity, private val titleList: Array<String>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = titleList.size

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MoviesFragment()
            1 -> TvShowFragment()
            else -> MoviesFragment()
        }
    }
}