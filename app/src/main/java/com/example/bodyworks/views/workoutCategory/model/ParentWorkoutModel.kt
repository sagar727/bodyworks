package com.example.bodyworks.model

data class ParentWorkoutModel(
    var categoryTitle: String,
    var childSubWorkoutItemList: MutableList<ChildSubWorkoutModel>
)
