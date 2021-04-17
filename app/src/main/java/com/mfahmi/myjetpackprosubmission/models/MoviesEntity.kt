package com.mfahmi.myjetpackprosubmission.models

data class MoviesEntity(val movieId: Int,
                        val moviePosterPath: String,
                        val movieTitle: String,
                        val movieReleaseDate: String,
                        val movieRating: Double,
                        val movieOverview: String,
                        val movieGenres: List<String>)