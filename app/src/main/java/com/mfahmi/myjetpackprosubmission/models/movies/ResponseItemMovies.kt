package com.mfahmi.myjetpackprosubmission.models.movies

import com.google.gson.annotations.SerializedName

data class ResponseItemMovies (
    @SerializedName("results")
    val results: List<ResponseMovie>
)