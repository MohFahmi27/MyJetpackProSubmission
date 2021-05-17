package com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow

import com.google.gson.annotations.SerializedName
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.TvShowEntity

data class ResponseItemTvShows(
    @SerializedName("results")
    val results: List<TvShowEntity>
)
