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

internal fun ImageView.setRoundedGlide(urlPath: String) {
    Glide.with(context).load("https://image.tmdb.org/t/p/w500/$urlPath")
            .apply(RequestOptions().override(140, 180)).apply(RequestOptions()
            .transform(RoundedCorners(8))).into(this)
}

internal fun View.setAnimation(animationId: Int ,animTime: Long, startOffset: Long) {
    startAnimation(AnimationUtils.loadAnimation(context, animationId).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    })
}