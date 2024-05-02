package com.example.johndavidmerciales_android_exam.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("Select * from personentity where id = :id")
    suspend fun getPersonByID(id: String?): PersonEntity
    @Upsert
    suspend fun upsertPerson(personEntity: List<PersonEntity>)

    @Query("SELECT * FROM personentity")
    fun getAllPersons(): Flow<List<PersonEntity>>

    @Query("DELETE FROM personentity")
    suspend fun clearAll()
}