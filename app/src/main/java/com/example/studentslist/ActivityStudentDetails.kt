package com.example.studentslist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class ActivityStudentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val toolbar = findViewById<MaterialToolbar>(R.id.student_details_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val nameField = findViewById<TextView>(R.id.student_details_name)
        val idField = findViewById<TextView>(R.id.student_details_id)
        val phoneField = findViewById<TextView>(R.id.student_details_phone)
        val addressField = findViewById<TextView>(R.id.student_details_address)
        val isPresentField = findViewById<CheckBox>(R.id.student_details_checkbox)
        val editButton = findViewById<Button>(R.id.edit_button_details)

        val studentId = intent.getStringExtra("STUDENT_ID")
        if (studentId.isNullOrBlank()) {
            Toast.makeText(this, "Missing student id", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // TODO: find student and show it's data

        editButton.setOnClickListener {
            val i = Intent(this, ActivityEditStudent::class.java)
            // TODO: send the id of the student to the edit form
            startActivity(i)

            finish()
        }
    }
}
