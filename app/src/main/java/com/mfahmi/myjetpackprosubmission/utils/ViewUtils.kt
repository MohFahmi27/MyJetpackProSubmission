package com.mfahmi.myjetpackprosubmission.utils

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mfahmi.myjetpackprosubmission.R

object ViewUtils {
    internal fun setAnimation(context: Context, view: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim_items)
        view.startAnimation(animation)
    }

    internal fun setGlide(context: Context, urlPath: String, imageView: ImageView) {
        Glide.with(context).load(urlPath)
            .apply(RequestOptions().override(90, 140))
            .apply(RequestOptions().transform(RoundedCorners(15))).into(imageView)
    }
}