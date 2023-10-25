package com.example.bodyworks.dailyWorkoutPlanner.planWorkouts

data class EachDayModel(
    val day: String,
    var isExpandable: Boolean,
    val exerciseList: Array<String>
)
