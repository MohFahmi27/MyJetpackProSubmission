package com.mfahmi.myjetpackprosubmission.models.movies

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
	@SerializedName("overview")
	val overview: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("poster_path")
	val posterPath: String,

	@SerializedName("release_date")
	val releaseDate: String,

	@SerializedName("vote_average")
	val voteAverage: Double,

	@SerializedName("id")
	val id: Int,
)
