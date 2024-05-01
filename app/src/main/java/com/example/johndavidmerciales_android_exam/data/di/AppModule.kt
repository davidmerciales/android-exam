package com.example.johndavidmerciales_android_exam.data.di

import android.app.Application
import androidx.room.Room
import com.example.johndavidmerciales_android_exam.data.local.dao.PersonDao
import com.example.johndavidmerciales_android_exam.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "person.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: AppDatabase): PersonDao {
        return db.dao
    }
}