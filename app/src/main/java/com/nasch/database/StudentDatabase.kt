package com.nasch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nasch.Dao.StudentDao
import com.nasch.Entity.StudentEntity

@Database(entities = [StudentEntity::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun getStudentDao(): StudentDao

}