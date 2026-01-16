package com.example.studentslist

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val idView = itemView.findViewById<TextView>(R.id.students_list_item_id)
    val nameView = itemView.findViewById<TextView>(R.id.students_list_item_name)
    val avatarView = itemView.findViewById<ImageView>(R.id.students_list_item_avatar)
    val isCheckedView = itemView.findViewById<CheckBox>(R.id.students_list_item_is_checked)
}