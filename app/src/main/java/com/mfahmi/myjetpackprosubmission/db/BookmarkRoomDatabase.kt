package com.mfahmi.myjetpackprosubmission.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.utils.SingletonHolder

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class BookmarkRoomDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object : SingletonHolder<BookmarkRoomDatabase, Context>({
        Room.databaseBuilder(
            it.applicationContext, BookmarkRoomDatabase::class.java, "bookmark_catalogue.db"
        ).build()
    })
}