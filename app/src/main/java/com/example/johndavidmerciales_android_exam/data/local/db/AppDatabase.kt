package com.example.johndavidmerciales_android_exam.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.johndavidmerciales_android_exam.data.local.dao.PersonDao

@Database(
    entities = [PersonDao::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract val dao: PersonDao
}