package com.mfahmi.myjetpackprosubmission.utils

import android.view.View

class CustomOnItemClickListener(
    private val position: Int,
    private val onItemClickCallback: OnItemClickCallback
) : View.OnClickListener {

    interface OnItemClickCallback {
        fun onItemClicked(view: View, position: Int)
    }

    override fun onClick(v: View?) {
        onItemClickCallback.onItemClicked(v as View, position)
    }
}