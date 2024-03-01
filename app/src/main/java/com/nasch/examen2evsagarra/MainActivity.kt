package com.nasch.examen2evsagarra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.nasch.Entity.StudentEntity
import com.nasch.Entity.toDatabase
import com.nasch.database.StudentDatabase
import com.nasch.examen2evsagarra.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var room: StudentDatabase
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        room = Room.databaseBuilder(this, StudentDatabase::class.java, "student_db").build()

        fillDatabase()
        initUI()
    }

    private fun initUI() {

        adapter = StudentAdapter()
        binding.rvStudents.setHasFixedSize(true)
        binding.rvStudents.layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter
        binding.tvHeaderStudent.setOnClickListener{
            showAll()
        }
        binding.tvPass.setOnClickListener {
            showPass()
        }
        binding.tvFail.setOnClickListener {
            showFail()
        }
        CoroutineScope(Dispatchers.IO).launch {
        val listEntity: List<StudentEntity> = room.getStudentDao().getAll()
        runOnUiThread { adapter.updateList(listEntity)
            binding.progressBar.isVisible = false
        }
        }
    }
    private fun showPass() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val listEntity: List<StudentEntity> = room.getStudentDao().getPass()
            runOnUiThread { adapter.updateList(listEntity)
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun showAll() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val listEntity: List<StudentEntity> = room.getStudentDao().getAll()
            runOnUiThread { adapter.updateList(listEntity)
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun showFail() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val listEntity: List<StudentEntity> = room.getStudentDao().getFail()
            runOnUiThread { adapter.updateList(listEntity)
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun fillDatabase(){
        binding.progressBar.isVisible = true
        val names: List<String> = listOf("Briseida", "Anacleto", "Macaria", "Uldarico", "Pascasia",
            "Inolfo", "Pancracio", "Espaminondo", "Gervasio", "Patrocinio", "Hermenegilda",
            "Crescencio", "Cristobalina", "Agapito", "Tesifonte",   "Petronila", "Torcuato",
            "Vitorio", "Isidra", "Sibilia", "Ambrosio", "Delfina", "Tiburcio", "Margarito",
            "Filemón", "Crisóloga", "Casimiro", "Cananea", "Felipa", "Cojoncio" )
        val grades: List<Double> = listOf(5.2, 4.3, 9.8, 6.7, 7.8, 5.0, 8.9, 9.7, 10.0, 2.3,
            3.5, 6.4, 8.4, 9.2, 1.3, 4.9,   5.7, 6.2, 8.5, 9.9, 2.5, 4.6, 5.8, 9.7, 6.8, 8.2,
            8.9, 6.4, 4.0, 10.0)
        val studentWithGradeList = mutableListOf<Student>()
        for (i in 0..29){
            studentWithGradeList.add(Student(names[i], grades[i])) }
        val studentEntityList =  studentWithGradeList.map { it.toDatabase() }
        CoroutineScope(Dispatchers.Main).launch {
        room.getStudentDao().deleteAll()

        if(room.getStudentDao().getAll().isNotEmpty()){
            //nada
        }else{
            room.getStudentDao().insertAll(studentEntityList)
        }

        runOnUiThread{binding.progressBar.isVisible = false}
        }
    }
}


