package com.example.bodyworks.model

data class WorkoutDataModel(
    val name:String,
    val video: String,
    val thumbnail: String,
    val muscle:String
    ){
    var id:Int = 0
}
