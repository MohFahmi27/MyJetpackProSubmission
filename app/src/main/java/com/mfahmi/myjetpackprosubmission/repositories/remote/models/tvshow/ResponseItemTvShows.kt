package com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow

import com.google.gson.annotations.SerializedName

data class ResponseItemTvShows(
    @SerializedName("results")
    val results: List<ResponseTvShow>
)
