package com.example.bodyworks.database

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.EditText
import android.widget.Toast
import java.util.Calendar

val DATABASE_NAME = "UserDob.db"
val DATABASE_VERSION = 1
val TABLE_NAME = "Users"
val COL_ID = "id"
val COL_NAME = "name"
val COL_DOB = "dob"


class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_NAME TEXT,$COL_DOB TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(deleteTable)
    }

//    fun addData(user:User){
//        val db = this.writableDatabase
//        val content = ContentValues()
//        content.put(COL_NAME,user.name)
//        content.put(COL_DOB,user.dob)
//        val result = db.insert(TABLE_NAME,null,content)
//        if(result == (-1).toLong()){
//            Toast.makeText(context,"Could not add data!!",Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(context,"Data added successfully!!", Toast.LENGTH_LONG).show()
//        }
//        db.close()
//    }
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
//
//    fun updateData(user:User){
//        val db = this.writableDatabase
//        val content = ContentValues()
//        content.put(COL_NAME,user.name)
//        content.put(COL_DOB,user.dob)
//        val result = db.update(TABLE_NAME,content, "$COL_ID ="+user.id,null)
//        if(result == 0){
//            Toast.makeText(context,"Could not update data!!",Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(context,"Data updated successfully!!", Toast.LENGTH_LONG).show()
//        }
//        db.close()
//    }
//
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
//
//    fun dateDialog(date :EditText) {
//        val cal = Calendar.getInstance()
//
//        val year = cal.get(Calendar.YEAR)
//        val month = cal.get(Calendar.MONTH)
//        val day = cal.get(Calendar.DAY_OF_MONTH)
//
//        val datePicker = DatePickerDialog(context,DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                date.setText("" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year)
//            },year, month, day)
//        datePicker.show()
//    }
}