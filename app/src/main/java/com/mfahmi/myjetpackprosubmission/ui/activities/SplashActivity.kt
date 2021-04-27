package com.mfahmi.myjetpackprosubmission.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.ActivitySplashBinding
import com.mfahmi.myjetpackprosubmission.utils.setAnimation

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imageView.setAnimation(R.anim.slide_down, 900L, 0L)
        binding.tvSplashScreen.setAnimation(R.anim.slide_up, 900L, 0L)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }
}