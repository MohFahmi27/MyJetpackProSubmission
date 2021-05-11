package com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseTvShow(
	@SerializedName("first_air_date")
	val firstAirDate: String,

	@SerializedName("overview")
	val overview: String,

	@SerializedName("poster_path")
	val posterPath: String,

	@SerializedName("vote_average")
	val voteAverage: Double,

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: Int,
): Parcelable
