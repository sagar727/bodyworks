package com.example.bodyworks.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.bodyworks.model.User
import com.example.bodyworks.model.WorkoutDataModel

val DATABASE_NAME = "Bodyworks.db"
val DATABASE_VERSION = 1

val ACTIVITY_TABLE = "activity"
val ACTIVITY_ID = "actid"
val ACTIVITY_NAME = "actname"

val WORKOUT_ABDOMEN = "abdomen"
val ABDOMEN_ID = "abdomenid"
val ABDOMEN_NAME = "abdomenname"
val ABDOMEN_VIDEO = "abdomenvideo"
val ABDOMEN_MUSCLES = "abdomenmuscles"
val ABDOMEN_THUMBNAIL = "abdomenthumbnail"

val WORKOUT_BACK = "back"
val BACK_ID = "backid"
val BACK_NAME = "backname"
val BACK_VIDEO = "backvideo"
val BACK_MUSCLES = "backmuscles"
val BACK_THUMBNAIL = "backthumbnail"

val WORKOUT_BICEPS = "biceps"
val BICEPS_ID = "bicepsid"
val BICEPS_NAME = "bicepsname"
val BICEPS_VIDEO = "bicepsvideo"
val BICEPS_MUSCLES = "bicepsmuscles"
val BICEPS_THUMBNAIL = "bicepsthumbnail"

val WORKOUT_CARDIO = "cardio"
val CARDIO_ID = "cardioid"
val CARDIO_NAME = "cardioname"
val CARDIO_VIDEO = "cardiovideo"
val CARDIO_MUSCLES = "cardiomuscles"
val CARDIO_THUMBNAIL = "cardiothumbnail"

val WORKOUT_CHEST = "chest"
val CHEST_ID = "chestid"
val CHEST_NAME = "chestname"
val CHEST_VIDEO = "chestvideo"
val CHEST_MUSCLES = "chestmuscles"
val CHEST_THUMBNAIL = "chestthumbnail"

val WORKOUT_LEG = "leg"
val LEG_ID = "legid"
val LEG_NAME = "legname"
val LEG_VIDEO = "legvideo"
val LEG_MUSCLES = "legmuscles"
val LEG_THUMBNAIL = "legthumbnail"

val WORKOUT_SHOULDER = "shoulder"
val SHOULDER_ID = "shoulderid"
val SHOULDER_NAME = "shouldername"
val SHOULDER_VIDEO = "shouldervideo"
val SHOULDER_MUSCLES = "shouldermuscles"
val SHOULDER_THUMBNAIL = "shoulderthumbnail"

val WORKOUT_TRICEPS = "triceps"
val TRICEPS_ID = "tricepsid"
val TRICEPS_NAME = "tricepsname"
val TRICEPS_VIDEO = "tricepsvideo"
val TRICEPS_MUSCLES = "tricepsmuscles"
val TRICEPS_THUMBNAIL = "tricepsthumbnail"

val USER_TABLE = "user"
val USER_ID = "userid"
val USER_WT = "userwt"
val USER_BMI = "userbmi"

val PLANNER_TABLE = "planner"
val DAY_ID = "dayid"
val DAY_NAME = "dayname"
val ACTIVITY = "activity"


class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val createActivityTable =
            "CREATE TABLE $ACTIVITY_TABLE(" +
                    "$ACTIVITY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$ACTIVITY_NAME TEXT NOT NULL" +
                    ")"
        db?.execSQL(createActivityTable)

        val createAbdomenTable =
            "CREATE TABLE $WORKOUT_ABDOMEN(" +
                    "$ABDOMEN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$ABDOMEN_NAME TEXT NOT NULL, " +
                    "$ABDOMEN_VIDEO TEXT NOT NULL, " +
                    "$ABDOMEN_THUMBNAIL TEXT NOT NULL," +
                    "$ABDOMEN_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createAbdomenTable)

        val createBackTable =
            "CREATE TABLE $WORKOUT_BACK(" +
                    "$BACK_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$BACK_NAME TEXT NOT NULL, " +
                    "$BACK_VIDEO TEXT NOT NULL, " +
                    "$BACK_THUMBNAIL TEXT NOT NULL," +
                    "$BACK_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createBackTable)

        val createBicepsTable =
            "CREATE TABLE $WORKOUT_BICEPS(" +
                    "$BICEPS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$BICEPS_NAME TEXT NOT NULL, " +
                    "$BICEPS_VIDEO TEXT NOT NULL, " +
                    "$BICEPS_THUMBNAIL TEXT NOT NULL," +
                    "$BICEPS_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createBicepsTable)

        val createCardioTable =
            "CREATE TABLE $WORKOUT_CARDIO(" +
                    "$CARDIO_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$CARDIO_NAME TEXT NOT NULL, " +
                    "$CARDIO_VIDEO TEXT NOT NULL, " +
                    "$CARDIO_THUMBNAIL TEXT NOT NULL," +
                    "$CARDIO_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createCardioTable)

        val createChestTable =
            "CREATE TABLE $WORKOUT_CHEST(" +
                    "$CHEST_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$CHEST_NAME TEXT NOT NULL, " +
                    "$CHEST_VIDEO TEXT NOT NULL, " +
                    "$CHEST_THUMBNAIL TEXT NOT NULL," +
                    "$CHEST_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createChestTable)

        val createLegTable =
            "CREATE TABLE $WORKOUT_LEG(" +
                    "$LEG_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$LEG_NAME TEXT NOT NULL, " +
                    "$LEG_VIDEO TEXT NOT NULL, " +
                    "$LEG_THUMBNAIL TEXT NOT NULL," +
                    "$LEG_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createLegTable)

        val createShoulderTable =
            "CREATE TABLE $WORKOUT_SHOULDER(" +
                    "$SHOULDER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$SHOULDER_NAME TEXT NOT NULL, " +
                    "$SHOULDER_VIDEO TEXT NOT NULL, " +
                    "$SHOULDER_THUMBNAIL TEXT NOT NULL," +
                    "$SHOULDER_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createShoulderTable)

        val createTricepsTable =
            "CREATE TABLE $WORKOUT_TRICEPS(" +
                    "$TRICEPS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$TRICEPS_NAME TEXT NOT NULL, " +
                    "$TRICEPS_VIDEO TEXT NOT NULL, " +
                    "$TRICEPS_THUMBNAIL TEXT NOT NULL," +
                    "$TRICEPS_MUSCLES TEXT NOT NULL" +
                    ")"
        db?.execSQL(createTricepsTable)

        val createUserTable =
            "CREATE TABLE $USER_TABLE(" +
                    "$USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$USER_WT TEXT," +
                    "$USER_BMI TEXT" +
                    ")"
        db?.execSQL(createUserTable)

        val createPlannerTable =
            "CREATE TABLE $PLANNER_TABLE(" +
                    "$DAY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$DAY_NAME TEXT," +
                    "$ACTIVITY TEXT" +
                    ")"
        db?.execSQL(createPlannerTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteActivityTable = "DROP TABLE IF EXISTS $ACTIVITY_TABLE"
        db?.execSQL(deleteActivityTable)

        val deleteAbdomenTable = "DROP TABLE IF EXISTS $WORKOUT_ABDOMEN"
        db?.execSQL(deleteAbdomenTable)

        val deleteBackTable = "DROP TABLE IF EXISTS $WORKOUT_BACK"
        db?.execSQL(deleteBackTable)

        val deleteBicepsTable = "DROP TABLE IF EXISTS $WORKOUT_BICEPS"
        db?.execSQL(deleteBicepsTable)

        val deleteCardioTable = "DROP TABLE IF EXISTS $WORKOUT_CARDIO"
        db?.execSQL(deleteCardioTable)

        val deleteChestTable = "DROP TABLE IF EXISTS $WORKOUT_CHEST"
        db?.execSQL(deleteChestTable)

        val deleteLegTable = "DROP TABLE IF EXISTS $WORKOUT_LEG"
        db?.execSQL(deleteLegTable)

        val deleteShoulderTable = "DROP TABLE IF EXISTS $WORKOUT_SHOULDER"
        db?.execSQL(deleteShoulderTable)

        val deleteTricepsTable = "DROP TABLE IF EXISTS $WORKOUT_TRICEPS"
        db?.execSQL(deleteTricepsTable)

        val deleteUserTable = "DROP TABLE IF EXISTS $USER_TABLE"
        db?.execSQL(deleteUserTable)

        val deletePlannerTable = "DROP TABLE IF EXISTS $PLANNER_TABLE"
        db?.execSQL(deletePlannerTable)
    }

    fun addActivityData(activityData: Array<String>){
        val db = this.writableDatabase
        val content = ContentValues()
        for(activity in activityData){
            content.put(ACTIVITY_NAME,activity)
            val result = db.insert(ACTIVITY_TABLE,null,content)
            if(result == (-1).toLong()){
                Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
            }
        }
        db.close()
    }

    fun addAbdomenWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(ABDOMEN_NAME ,workout.name)
        content.put(ABDOMEN_MUSCLES,workout.muscle)
        content.put(ABDOMEN_VIDEO,workout.video)
        content.put(ABDOMEN_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_ABDOMEN,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addBackWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(BACK_NAME ,workout.name)
        content.put(BACK_MUSCLES,workout.muscle)
        content.put(BACK_VIDEO,workout.video)
        content.put(BACK_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_BACK,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addBicepWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(BICEPS_NAME ,workout.name)
        content.put(BICEPS_MUSCLES,workout.muscle)
        content.put(BICEPS_VIDEO,workout.video)
        content.put(BICEPS_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_BICEPS,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addCardioWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(CARDIO_NAME ,workout.name)
        content.put(CARDIO_MUSCLES,workout.muscle)
        content.put(CARDIO_VIDEO,workout.video)
        content.put(CARDIO_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_CARDIO,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addChestWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(CHEST_NAME ,workout.name)
        content.put(CHEST_MUSCLES,workout.muscle)
        content.put(CHEST_VIDEO,workout.video)
        content.put(CHEST_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_CHEST,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addLegWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(LEG_NAME ,workout.name)
        content.put(LEG_MUSCLES,workout.muscle)
        content.put(LEG_VIDEO,workout.video)
        content.put(LEG_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_LEG,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addShoulderWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(SHOULDER_NAME ,workout.name)
        content.put(SHOULDER_MUSCLES,workout.muscle)
        content.put(SHOULDER_VIDEO,workout.video)
        content.put(SHOULDER_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_SHOULDER,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addTricepWorkoutData(workout:WorkoutDataModel){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(TRICEPS_NAME ,workout.name)
        content.put(TRICEPS_MUSCLES,workout.muscle)
        content.put(TRICEPS_VIDEO,workout.video)
        content.put(TRICEPS_THUMBNAIL,workout.thumbnail)
        val result = db.insert(
            WORKOUT_TRICEPS,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun addUserData(user: User){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(USER_WT,user.wt)
        content.put(USER_BMI,user.bmi)
        val result = db.insert(USER_TABLE,null,content)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    @SuppressLint("Range")
    fun getBMI(): String{
        val selectQuery = "SELECT * FROM $USER_TABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var bmi: String = "0"
        cursor = db.rawQuery(selectQuery,null)
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    bmi = cursor.getString(cursor.getColumnIndex(USER_BMI))
                }while (cursor.moveToNext())
            }
        }
        return bmi
    }

    fun countTableRow(tableName: String):Int{
        val db = this.readableDatabase
        val count = DatabaseUtils.queryNumEntries(db, tableName)
        db.close()
        return count.toInt()
    }
//
//    fun deleteAll(){
//        val db = this.writableDatabase
//        val result = db.delete(TABLE_NAME, null,null)
//        if(result == 0){
//            Toast.makeText(context,"Could not delete data!!",Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(context,"Reset done successfully!!", Toast.LENGTH_LONG).show()
//        }
//        db.close()
//    }

    fun updateUserData(user:User){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put(USER_WT,user.wt)
        content.put(USER_BMI,user.bmi)
        val result = db.update(USER_TABLE,content, null,null)
        if(result == 0){
            Toast.makeText(context,"Could not update data!!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Data updated successfully!!", Toast.LENGTH_LONG).show()
        }
        db.close()
    }

//    fun deleteData(id:Int){
//        val db = this.writableDatabase
//        val result = db.delete(TABLE_NAME, "$COL_ID =$id",null)
//        if(result == 0){
//            Toast.makeText(context,"Could not delete data!!",Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(context,"Data deleted successfully!!", Toast.LENGTH_LONG).show()
//        }
//        db.close()
//    }
//
//    @SuppressLint("Range")
//    fun displayAll():ArrayList<User>{
//        val userData = ArrayList<User>()
//        val selectQuery = "SELECT * FROM $TABLE_NAME"
//        val db = this.readableDatabase
//        var cursor: Cursor? = null
//        cursor = db.rawQuery(selectQuery,null)
//        var id:Int
//        var name:String
//        var dob:String
//        if (cursor != null) {
//            if(cursor.moveToFirst()){
//                do{
//                    id = cursor.getInt(cursor.getColumnIndex(COL_ID))
//                    name = cursor.getString(cursor.getColumnIndex(COL_NAME))
//                    dob = cursor.getString(cursor.getColumnIndex(COL_DOB))
//                    val user = User(name,dob)
//                    user.id = id
//                    userData.add(user)
//                }while (cursor.moveToNext())
//            }
//        }
//        return userData
//    }
}