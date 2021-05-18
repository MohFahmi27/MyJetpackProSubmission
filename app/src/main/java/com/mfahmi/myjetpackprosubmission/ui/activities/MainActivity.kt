package com.mfahmi.myjetpackprosubmission.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.ActivityMainBinding
import com.mfahmi.myjetpackprosubmission.ui.adapter.MainPagerAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarMain)
        title = ""
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bookmark -> {
                startActivity(Intent(this, BookmarkActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}