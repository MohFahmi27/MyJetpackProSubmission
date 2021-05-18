package com.mfahmi.myjetpackprosubmission.data.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    @SerializedName("id")
    val movieId: Int,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Double,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "is_bookmark")
    var isBookmark: Boolean = false
) : Parcelable