package com.example.hamsterspace

import androidx.annotation.DrawableRes

data class Hamster(
    @DrawableRes val imageHamster: Int,
    val title: String,
    val description: String
)