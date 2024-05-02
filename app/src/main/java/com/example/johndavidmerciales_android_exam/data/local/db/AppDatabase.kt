package com.example.johndavidmerciales_android_exam.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.johndavidmerciales_android_exam.data.local.dao.PersonDao
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.Person

@Database(
    entities = [PersonEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract val dao: PersonDao
}