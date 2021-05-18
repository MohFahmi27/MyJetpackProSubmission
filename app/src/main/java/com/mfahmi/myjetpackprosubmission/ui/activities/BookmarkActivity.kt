package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.ActivityBookmarkBinding
import com.mfahmi.myjetpackprosubmission.ui.adapter.FavoritePagerAdapter

class BookmarkActivity : AppCompatActivity() {
    private val binding: ActivityBookmarkBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackBookmark.setOnClickListener { finish() }
        initView()
    }

    private fun initView() {
        binding.viewPagerBookmark.adapter = FavoritePagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerBookmark.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val tabTitle = resources.getStringArray(R.array.main_tab_title)
        TabLayoutMediator(binding.tabLayoutBookmark, binding.viewPagerBookmark) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

}