package com.mfahmi.myjetpackprosubmission.repositories.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_tv_show")
@Parcelize
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tv_show_id")
    @SerializedName("id")
    val tvShowId: Int,

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Double,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "is_bookmark")
    var isBookmark: Boolean = false
): Parcelable
