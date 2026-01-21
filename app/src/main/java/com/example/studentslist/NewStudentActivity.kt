package com.example.studentslist

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslist.models.Student
import com.example.studentslist.repositories.StudentsRepository
import com.google.android.material.appbar.MaterialToolbar

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_student_activity)

        val toolbar = findViewById<MaterialToolbar>(R.id.student_new_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val nameField = findViewById<EditText>(R.id.student_new_name)
        val idField = findViewById<EditText>(R.id.student_new_id)
        val phoneField = findViewById<EditText>(R.id.student_new_phone)
        val addressField = findViewById<EditText>(R.id.student_new_address)
        val isCheckedField = findViewById<CheckBox>(R.id.student_new_checkbox)

        val cancelButton = findViewById<Button>(R.id.cancel_button_new)
        val saveButton = findViewById<Button>(R.id.save_button_new)

        cancelButton.setOnClickListener { finish() }

        saveButton.setOnClickListener {
            val name = nameField.text?.toString()?.trim().orEmpty()
            val id = idField.text?.toString()?.trim().orEmpty()
            val phone = phoneField.text?.toString()?.trim().orEmpty()
            val address = addressField.text?.toString()?.trim().orEmpty()
            val isChecked = isCheckedField.isChecked

            if (name.isBlank() || id.isBlank() || phone.isBlank() || address.isBlank()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Prevent duplicate Ids
            val exists = StudentsRepository.students.any { s -> s.id == id }
            if (exists) {
                Toast.makeText(this, "Student ID already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentsRepository.students.add(
                Student(
                    id = id,
                    name = name,
                    isChecked = isChecked,
                    phoneNumber = phone,
                    address = address
                )
            )

            Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

