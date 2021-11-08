package com.jjac.moviest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jjac.moviest.database.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity ORDER BY timeStamp DESC")
    fun getPreviousVisitedMovies(): LiveData<List<MovieEntity>>

    @Insert
    fun insert(movie: MovieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieEntity)

    @Query("SELECT EXISTS (SELECT * FROM MovieEntity WHERE trackId = :track)")
    fun isExists(track : Int): LiveData<Boolean>
}