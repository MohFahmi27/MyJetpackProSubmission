package com.mfahmi.myjetpackprosubmission.models.tvshow

import com.google.gson.annotations.SerializedName

data class ResponseItemTvShows(
    @SerializedName("results")
    val results: List<ResponseTvShow>
)
