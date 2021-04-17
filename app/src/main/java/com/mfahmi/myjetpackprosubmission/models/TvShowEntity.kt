package com.mfahmi.myjetpackprosubmission.models

data class TvShowEntity(val tvShowId: Int,
                        val tvShowPosterPath: String,
                        val tvShowTitle: String,
                        val tvShowReleaseDate: String,
                        val tvShowRating: Double,
                        val tvShowOverview: String,
                        val tvShowGenres: List<String>)