package com.nasch.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasch.Entity.StudentEntity

@Dao
interface StudentDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(students: List<StudentEntity>)

    @Query("SELECT * FROM student_table")
    suspend fun getAll():List<StudentEntity>

    @Query ("DELETE FROM student_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM student_table WHERE grade> 5" )
    suspend fun getPass():List<StudentEntity>

    @Query("SELECT * FROM student_table WHERE grade< 5" )
    suspend fun getFail():List<StudentEntity>
}