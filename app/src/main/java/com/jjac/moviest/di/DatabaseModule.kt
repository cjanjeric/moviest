package com.jjac.moviest.di

import android.content.Context
import androidx.room.Room
import com.jjac.moviest.database.MoviestDb
import com.jjac.moviest.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MoviestDb{
        return Room.databaseBuilder(
            context,
            MoviestDb::class.java,
            "app.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(movieDao: MovieDao): MovieDao {
        return movieDao
    }
}