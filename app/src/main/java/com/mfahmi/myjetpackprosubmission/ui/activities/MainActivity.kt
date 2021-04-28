package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.adapter.MainPagerAdapter
import com.mfahmi.myjetpackprosubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.viewPagerMain.adapter =
            MainPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerMain.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val tabTitle = resources.getStringArray(R.array.main_tab_title)
        TabLayoutMediator(
            binding.tabLayoutMain, binding.viewPagerMain
        ) { tab, position -> tab.text = tabTitle[position] }.attach()
    }

}