package com.example.studentslist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.adapters.StudentsAdapter
import com.example.studentslist.models.Student
import com.example.studentslist.repositories.StudentsRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var noStudentsMessage: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById<RecyclerView>(R.id.students_list)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        noStudentsMessage = findViewById(R.id.no_students_message)

        initRecyclerView()
        setNoStudentsMessage()

        findViewById<FloatingActionButton>(R.id.add_student).setOnClickListener {
            val intent = Intent(this, ActivityNewStudent::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
        setNoStudentsMessage()
    }

    private fun initRecyclerView() {
        adapter = StudentsAdapter(
            StudentsRepository.students,
            onStudentItemClick = { student: Student ->
                val intent = Intent(this, ActivityStudentDetails::class.java)
                intent.putExtra("STUDENT_ID", student.id)
                startActivity(intent)
            },
            onCheckBoxClick = { student, isChecked ->
                student.isChecked = isChecked
            })
        recyclerView.adapter = adapter
    }

    private fun setNoStudentsMessage() {
        val hasStudents = StudentsRepository.students.isNotEmpty()

        recyclerView.visibility = if (hasStudents) View.VISIBLE else View.GONE
        noStudentsMessage.visibility = if (hasStudents) View.GONE else View.VISIBLE
    }
}