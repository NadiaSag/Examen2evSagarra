package com.nasch.examen2evsagarra

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nasch.Entity.StudentEntity
import com.nasch.examen2evsagarra.databinding.ItemStudentBinding

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemStudentBinding.bind(view)

    fun bind(studentEntity: StudentEntity) {
        binding.tvStudent.text = studentEntity.name
        binding.tvGrade.text = studentEntity.grade.toString()
    }
}