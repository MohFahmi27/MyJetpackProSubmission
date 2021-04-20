package com.mfahmi.myjetpackprosubmission.utils

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mfahmi.myjetpackprosubmission.R

internal fun View.setAnimationRecyclerView() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim_items))
}

internal fun ImageView.setGlide(urlPath: String) {
    Glide.with(context).load(urlPath).apply(RequestOptions().override(100, 150))
            .apply(RequestOptions().transform(RoundedCorners(10))).into(this)
}

internal fun View.slideUpAnimation(animTime: Long, startOffset: Long) {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_up).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    })
}

internal fun View.slideDownAnimation(animTime: Long, startOffset: Long) {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_down).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    })
}