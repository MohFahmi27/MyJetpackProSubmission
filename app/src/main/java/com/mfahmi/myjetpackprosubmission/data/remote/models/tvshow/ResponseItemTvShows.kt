package com.mfahmi.myjetpackprosubmission.data.remote.models.tvshow

import com.google.gson.annotations.SerializedName
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity

data class ResponseItemTvShows(
    @SerializedName("results")
    val results: List<TvShowEntity>
)
