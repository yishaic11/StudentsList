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

class ActivityEditStudent : AppCompatActivity() {

    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val toolbar = findViewById<MaterialToolbar>(R.id.student_edit_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val nameField = findViewById<EditText>(R.id.student_edit_name)
        val idField = findViewById<EditText>(R.id.student_edit_id)
        val phoneField = findViewById<EditText>(R.id.student_edit_phone)
        val addressField = findViewById<EditText>(R.id.student_edit_address)
        val isCheckedField = findViewById<CheckBox>(R.id.student_edit_checkbox)

        val cancelButton = findViewById<Button>(R.id.cancel_button_edit)
        val deleteButton = findViewById<Button>(R.id.delete_button_edit)
        val saveButton = findViewById<Button>(R.id.save_button_edit)

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

        fun fillForm(s: Student) {
            nameField.setText(s.name)
            idField.setText(s.id)
            phoneField.setText(s.phoneNumber)
            addressField.setText(s.address)
            isCheckedField.isChecked = s.isChecked
        }

        fillForm(found)

        cancelButton.setOnClickListener { finish() }

        deleteButton.setOnClickListener {
            student?.let { StudentsRepository.students.remove(it) }
            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            finish()
        }

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
            val duplicate = StudentsRepository.students.firstOrNull { s -> s.id == id }
            if (duplicate != null && duplicate !== student) {
                Toast.makeText(this, "Student ID already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            student?.apply {
                this.name = name
                this.id = id
                this.phoneNumber = phone
                this.address = address
                this.isChecked = isChecked
            }

            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}