package com.rcosteira.recyclerviewexample.presentation.entities

data class DisplayedUser(
    val id: Int = -1,
    val name: String = "",
    var isChecked: Boolean = false
    //val avatarImage: Bitmap
)
