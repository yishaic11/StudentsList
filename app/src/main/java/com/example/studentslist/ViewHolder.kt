package com.example.studentslist

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val id = itemView.findViewById<TextView>(R.id.student_id)
    val name = itemView.findViewById<TextView>(R.id.student_name)
    val avatar = itemView.findViewById<ImageView>(R.id.student_avatar)
    val isPresentCheckBox = itemView.findViewById<CheckBox>(R.id.is_present)
}