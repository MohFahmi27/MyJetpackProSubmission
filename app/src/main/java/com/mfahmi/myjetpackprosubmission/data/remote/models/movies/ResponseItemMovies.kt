package com.mfahmi.myjetpackprosubmission.data.remote.models.movies

import com.google.gson.annotations.SerializedName
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity

data class ResponseItemMovies (
    @SerializedName("results")
    val results: List<MovieEntity>
)