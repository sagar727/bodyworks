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
val ABDOMEN_ID = "id"
val ABDOMEN_NAME = "name"
val ABDOMEN_VIDEO = "video"
val ABDOMEN_MUSCLES = "muscles"
val ABDOMEN_THUMBNAIL = "thumbnail"

val WORKOUT_BACK = "back"
val BACK_ID = "id"
val BACK_NAME = "name"
val BACK_VIDEO = "video"
val BACK_MUSCLES = "muscles"
val BACK_THUMBNAIL = "thumbnail"

val WORKOUT_BICEPS = "biceps"
val BICEPS_ID = "id"
val BICEPS_NAME = "name"
val BICEPS_VIDEO = "video"
val BICEPS_MUSCLES = "muscles"
val BICEPS_THUMBNAIL = "thumbnail"

val WORKOUT_CARDIO = "cardio"
val CARDIO_ID = "id"
val CARDIO_NAME = "name"
val CARDIO_VIDEO = "video"
val CARDIO_MUSCLES = "muscles"
val CARDIO_THUMBNAIL = "thumbnail"

val WORKOUT_CHEST = "chest"
val CHEST_ID = "id"
val CHEST_NAME = "name"
val CHEST_VIDEO = "video"
val CHEST_MUSCLES = "muscles"
val CHEST_THUMBNAIL = "thumbnail"

val WORKOUT_LEG = "leg"
val LEG_ID = "id"
val LEG_NAME = "name"
val LEG_VIDEO = "video"
val LEG_MUSCLES = "muscles"
val LEG_THUMBNAIL = "thumbnail"

val WORKOUT_SHOULDER = "shoulder"
val SHOULDER_ID = "id"
val SHOULDER_NAME = "name"
val SHOULDER_VIDEO = "video"
val SHOULDER_MUSCLES = "muscles"
val SHOULDER_THUMBNAIL = "thumbnail"

val WORKOUT_TRICEPS = "triceps"
val TRICEPS_ID = "id"
val TRICEPS_NAME = "name"
val TRICEPS_VIDEO = "video"
val TRICEPS_MUSCLES = "muscles"
val TRICEPS_THUMBNAIL = "thumbnail"

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

    fun addWorkoutData(workout:WorkoutDataModel, tableName: String){
        val db = this.writableDatabase
        val content = ContentValues()
        content.put("name" ,workout.name)
        content.put("video",workout.video)
        content.put("muscles",workout.muscle)
        content.put("thumbnail",workout.thumbnail)
        val result = db.insert(
            tableName,null,content)
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


    @SuppressLint("Range")
    fun displayAll(tableName: String):ArrayList<WorkoutDataModel>{
        val workOutData = ArrayList<WorkoutDataModel>()
        val selectQuery = "SELECT * FROM $tableName"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        cursor = db.rawQuery(selectQuery,null)
        var id:Int
        var workoutName:String
        var video:String
        var muscle:String
        var thumbnail:String
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    id = cursor.getInt(cursor.getColumnIndex("id"))
                    workoutName = cursor.getString(cursor.getColumnIndex("name"))
                    video = cursor.getString(cursor.getColumnIndex("video"))
                    muscle = cursor.getString(cursor.getColumnIndex("muscles"))
                    thumbnail = cursor.getString(cursor.getColumnIndex("thumbnail"))
                    val workData = WorkoutDataModel(workoutName,video,thumbnail,muscle)
                    workData.id = id
                    workOutData.add(workData)
                }while (cursor.moveToNext())
            }
        }
        return workOutData
    }

    @SuppressLint("Range")
    fun getWorkoutDetail(workoutName:String, tableName: String): WorkoutDataModel? {
        val selectQuery = "SELECT * FROM $tableName WHERE name = ?"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        cursor = db.rawQuery(selectQuery, arrayOf(workoutName))
        var id:Int
        var workoutName:String
        var video:String
        var muscle:String
        var thumbnail:String
        var workData: WorkoutDataModel? = null
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    id = cursor.getInt(cursor.getColumnIndex("id"))
                    workoutName = cursor.getString(cursor.getColumnIndex("name"))
                    video = cursor.getString(cursor.getColumnIndex("video"))
                    muscle = cursor.getString(cursor.getColumnIndex("muscles"))
                    thumbnail = cursor.getString(cursor.getColumnIndex("thumbnail"))
                    workData = WorkoutDataModel(workoutName,video,thumbnail,muscle)
                    workData.id = id
                }while (cursor.moveToNext())
            }
        }
        return workData
    }
}