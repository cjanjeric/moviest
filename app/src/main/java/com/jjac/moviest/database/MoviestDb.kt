package com.jjac.moviest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jjac.moviest.database.dao.MovieDao
import com.jjac.moviest.database.entity.MovieEntity
import com.jjac.moviest.util.DateConverter

@Database( entities = [MovieEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MoviestDb : RoomDatabase() {
        abstract val movieDao: MovieDao
}