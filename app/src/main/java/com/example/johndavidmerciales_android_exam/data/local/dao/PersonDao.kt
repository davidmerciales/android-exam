package com.example.johndavidmerciales_android_exam.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity

@Dao
interface PersonDao {

    @Upsert
    fun upsertPerson(personEntity: List<PersonEntity>)

    @Query("DELETE FROM personentity")
    suspend fun clearAll()
}