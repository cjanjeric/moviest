package com.jjac.moviest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jjac.moviest.database.entity.MovieEntity
import com.jjac.store.domain.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity WHERE timeStamp IS NOT NULL ORDER BY timeStamp DESC")
    fun getPreviousVisitedMovies(): LiveData<List<MovieEntity>>

    @Insert
    fun insert(movie: MovieEntity)

    fun updateMovies(movies: List<MovieEntity>){
        deleteMovies()
        insertAll(movies)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM MovieEntity WHERE timeStamp IS NOT NULL")
    fun deleteMovies()

    @Query("SELECT * FROM MovieEntity WHERE timeStamp IS NULL")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieEntity)

    @Query("SELECT EXISTS (SELECT * FROM MovieEntity WHERE trackId = :track)")
    fun isExists(track : Int): LiveData<Boolean>
}