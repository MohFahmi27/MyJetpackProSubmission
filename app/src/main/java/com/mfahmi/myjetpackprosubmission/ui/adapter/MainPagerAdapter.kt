package com.mfahmi.myjetpackprosubmission.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.ui.fragments.TvShowFragment

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: ArrayList<Fragment> = arrayListOf(MoviesFragment(), TvShowFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}