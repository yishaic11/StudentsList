package com.example.studentslist.models


data class Student(
    var id: String,
    var name: String,
    var isPresent: Boolean = false,
    var phoneNumber: String,
    var address: String
)
