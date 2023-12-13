package com.example.bodyworks.views.nutritionalValues.model

data class FoodDataModel(
    val title: String,
    val pictureURL: Int,
    val description: String,
    var isExpandable: Boolean = false,
)
