package com.example.bodyworks.views.dailyWorkoutPlanner.planWorkouts

/**
 * Author: Ketul Chauhan
 * Date: October 25, 2023
 */
data class EachDayModel(
    val day: String,
    var isExpandable: Boolean,
    val exerciseList: Array<String>
)
