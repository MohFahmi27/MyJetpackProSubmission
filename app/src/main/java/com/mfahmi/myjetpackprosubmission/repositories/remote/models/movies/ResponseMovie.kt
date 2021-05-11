package com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable
