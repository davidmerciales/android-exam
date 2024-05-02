package com.example.johndavidmerciales_android_exam.data.di

import com.example.johndavidmerciales_android_exam.data.local.dao.PersonDao
import com.example.johndavidmerciales_android_exam.data.remote.ApiService
import com.example.johndavidmerciales_android_exam.data.repository.PersonEntityRepositoryImpl
import com.example.johndavidmerciales_android_exam.data.repository.PersonRepositoryImpl
import com.example.johndavidmerciales_android_exam.domain.repository.PersonEntityRepository
import com.example.johndavidmerciales_android_exam.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesPersonRepository(apiService: ApiService): PersonRepository{
        return PersonRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesPersonEntityRepositoryImpl(dao: PersonDao): PersonEntityRepository{
        return PersonEntityRepositoryImpl(dao)
    }
}