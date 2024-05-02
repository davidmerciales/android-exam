package com.example.johndavidmerciales_android_exam.domain.repository

import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonEntityRepository {

    suspend fun getPersonById(id: String?): PersonEntity
    suspend fun insertPersons(person: List<PersonEntity>)

    suspend fun getPersons(): Flow<List<PersonEntity>>
}