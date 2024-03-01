package com.nasch.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nasch.examen2evsagarra.Student

@Entity(tableName = "student_table")

data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Grade") val grade: Double
)

fun Student.toDatabase() = StudentEntity(name = name, grade = grade)
