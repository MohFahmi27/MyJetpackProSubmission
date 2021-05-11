package com.mfahmi.myjetpackprosubmission.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mfahmi.myjetpackprosubmission.ui.fragments.MovieFavoriteFragment
import com.mfahmi.myjetpackprosubmission.ui.fragments.TvShowFavoriteFragment

class FavoritePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: ArrayList<Fragment> =
        arrayListOf(MovieFavoriteFragment(), TvShowFavoriteFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}