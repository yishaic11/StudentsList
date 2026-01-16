package com.example.studentslist

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class ActivityEditStudent : AppCompatActivity() {
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
        val isPresentField = findViewById<CheckBox>(R.id.student_edit_checkbox)

        val cancelButton = findViewById<Button>(R.id.cancel_button_edit)
        val deleteButton = findViewById<Button>(R.id.delete_button_edit)
        val saveButton = findViewById<Button>(R.id.save_button_edit)

        val studentId = intent.getStringExtra("STUDENT_ID")
        if (studentId.isNullOrBlank()) {
            Toast.makeText(this, "Missing student id", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // TODO: fill form with student data

        cancelButton.setOnClickListener { finish() }

        deleteButton.setOnClickListener {
            // TODO: delete student
            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            finish()
        }

        saveButton.setOnClickListener {
            val name = nameField.text?.toString()?.trim().orEmpty()
            val id = idField.text?.toString()?.trim().orEmpty()
            val phone = phoneField.text?.toString()?.trim().orEmpty()
            val address = addressField.text?.toString()?.trim().orEmpty()
            val isPresent = isPresentField.isChecked

            if (name.isBlank() || id.isBlank() || phone.isBlank() || address.isBlank()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: save student updated data

            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
