package com.example.studentslist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslist.models.Student
import com.example.studentslist.repositories.StudentsRepository
import com.google.android.material.appbar.MaterialToolbar

class StudentDetailsActivity : AppCompatActivity() {

    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_details_activity)

        val toolbar = findViewById<MaterialToolbar>(R.id.student_details_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val nameField = findViewById<TextView>(R.id.student_details_name)
        val idField = findViewById<TextView>(R.id.student_details_id)
        val phoneField = findViewById<TextView>(R.id.student_details_phone)
        val addressField = findViewById<TextView>(R.id.student_details_address)
        val isCheckedField = findViewById<CheckBox>(R.id.student_details_checkbox)
        val editButton = findViewById<Button>(R.id.edit_button_details)

        val studentId = intent.getStringExtra("STUDENT_ID")
        if (studentId.isNullOrBlank()) {
            Toast.makeText(this, "Missing student id", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val found = StudentsRepository.students.firstOrNull { s -> s.id == studentId }
        if (found == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        student = found

        nameField.text = found.name
        idField.text = found.id
        phoneField.text = found.phoneNumber
        addressField.text = found.address
        isCheckedField.isChecked = found.isChecked

        isCheckedField.setOnCheckedChangeListener { _, isChecked ->
            student?.isChecked = isChecked
        }

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", student!!.id)
            startActivity(intent)

            finish()
        }
    }
}

