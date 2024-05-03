package com.example.johndavidmerciales_android_exam.data.repository

import com.example.johndavidmerciales_android_exam.data.local.dao.PersonDao
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.Person
import com.example.johndavidmerciales_android_exam.domain.repository.PersonEntityRepository
import com.example.johndavidmerciales_android_exam.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class PersonEntityRepositoryImpl(private val personDao: PersonDao) : PersonEntityRepository{
    override suspend fun getPersonById(id: String?): PersonEntity {
        return personDao.getPersonByID(id)
    }


    override suspend fun insertPersons(person: List<PersonEntity>) {
        personDao.upsertPerson(person)
    }

    override suspend fun getPersons(): Flow<List<PersonEntity>> {
        return personDao.getAllPersons()
    }

    override suspend fun deleteAllPersons() {
        personDao.clearAll()
    }

}