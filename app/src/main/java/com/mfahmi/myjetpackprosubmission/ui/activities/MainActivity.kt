package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.adapter.MainPagerAdapter
import com.mfahmi.myjetpackprosubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val tabTitle = resources.getStringArray(R.array.main_tab_title)
        binding.viewPagerMain.adapter =
            MainPagerAdapter(this, tabTitle)
        TabLayoutMediator(
            binding.tabLayoutMain,
            binding.viewPagerMain
        ) { tab, position -> tab.text = tabTitle[position] }.attach()
    }
}