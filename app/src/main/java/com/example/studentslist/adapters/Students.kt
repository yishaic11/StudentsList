package com.example.studentslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.R
import com.example.studentslist.ViewHolder
import com.example.studentslist.models.Student

class StudentsAdapter(
    private val data: List<Student>,
    private val onStudentItemClick: (Student) -> Unit,
    private val onCheckBoxClick: (Student, Boolean) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.students_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = data[position]

        holder.id?.text = student.id
        holder.name?.text = student.name
        holder.avatar?.setImageResource(R.drawable.student_avatar)
        holder.isPresentCheckBox.isChecked = student.isPresent

        holder.itemView.setOnClickListener { onStudentItemClick(student) }
        holder.isPresentCheckBox.setOnCheckedChangeListener { _, isChecked ->
            onCheckBoxClick(
                student,
                isChecked
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
