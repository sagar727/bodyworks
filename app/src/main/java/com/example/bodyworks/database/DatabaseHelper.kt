package com.example.bodyworks.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.bodyworks.model.User

val DATABASE_NAME = "Bodyworks.db"
val DATABASE_VERSION = 1

val ACTIVITY_TABLE = "activity"
val ACTIVITY_ID = "actid"
val ACTIVITY_NAME = "actname"

val WORKOUT_TABLE = "workout"
val WORKOUT_ID = "workoutid"
val WORKOUT_NAME = "workoutname"
val ACT_ID = "actid"
val WORKOUT_VIDEO = "workoutvideo"
val WORKOUT_MUSCLES = "workoutmuscles"
val WORKOUT_THUMBNAIL = "workoutthumbnail"

val USER_TABLE = "user"
val USER_ID = "userid"
val USER_WT = "userwt"
val USER_BMI = "userbmi"

val PLANNER_TABLE = "planner"
val DAY_ID = "dayid"
val DAY_NAME = "dayname"
val ACTIVITY1 = "activity1"
val ACTIVITY2 = "activity2"


class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val createActivityTable =
            "CREATE TABLE $ACTIVITY_TABLE(" +
                    "$ACTIVITY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$ACTIVITY_NAME TEXT NOT NULL" +
                    ")"
        db?.execSQL(createActivityTable)

        val createWorkoutTable =
            "CREATE TABLE $WORKOUT_TABLE(" +
                    "$WORKOUT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$WORKOUT_NAME TEXT NOT NULL, " +
                    "$WORKOUT_VIDEO TEXT NOT NULL, " +
                    "$WORKOUT_THUMBNAIL TEXT NOT NULL," +
                    "$WORKOUT_MUSCLES TEXT NOT NULL," +
                    "$ACT_ID INTEGER NOT NULL, " +
                    "FOREIGN KEY($ACT_ID) REFERENCES $ACTIVITY_TABLE($ACTIVITY_ID)" +
                    ")"
        db?.execSQL(createWorkoutTable)

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
                    "$ACTIVITY1 TEXT," +
                    "$ACTIVITY2 TEXT" +
                    ")"
        db?.execSQL(createPlannerTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteActivityTable = "DROP TABLE IF EXISTS $ACTIVITY_TABLE"
        db?.execSQL(deleteActivityTable)

        val deleteWorkoutTable = "DROP TABLE IF EXISTS $WORKOUT_TABLE"
        db?.execSQL(deleteWorkoutTable)

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

    fun addWorkoutData(){
        val db = this.writableDatabase
        val content = ContentValues()
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

    fun countUser():Int{
        val db = this.readableDatabase
        val count = DatabaseUtils.queryNumEntries(db, USER_TABLE)
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