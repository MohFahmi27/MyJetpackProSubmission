package com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies

import com.google.gson.annotations.SerializedName
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity

data class ResponseItemMovies (
    @SerializedName("results")
    val results: List<MovieEntity>
)