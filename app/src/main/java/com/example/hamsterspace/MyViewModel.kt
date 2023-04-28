package com.example.hamsterspace

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val hamsters = listOf(
        Hamster(R.drawable.hamster1, "Hamster 1", "Very big hamster."),
        Hamster(R.drawable.hamster2, "Hamster 2", "Very hungry hamster."),
        Hamster(R.drawable.hamster3, "Hamster 3", "Very angry hamster."),
    )
    var currentIndex: Int by mutableStateOf(0)
}