package com.example.spreadsheet.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.spreadsheet.util.military
import com.example.spreadsheet.util.pelList


class myDatabase(context: Context) : SQLiteOpenHelper(context , DATABASE_NAME, null , DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 15
        private const val DATABASE_NAME = "database.db"
        const val TABLE_NAME = "militaries"
        const val COLUMN_ID = "id"
        const val COLUMN_WAR_NAME = "warName"
        const val COLUMN_PATENT = "patent"
        const val COLUMN_STATE = "state"

    }

    override fun onCreate(db: SQLiteDatabase) {

        Log.i("teste", "" + "onCreate")

        val CREATE_TABLE = ("CREATE TABLE ${TABLE_NAME} (" +
                "${COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COLUMN_WAR_NAME} TEXT, " +
                "${COLUMN_PATENT} TEXT, " +
                "${COLUMN_STATE} TEXT)")
        db.execSQL(CREATE_TABLE)

       addDefaultList(db)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

    fun addDefaultList(db: SQLiteDatabase){

        pelList().forEach { item  ->
            Log.i("teste", "" + item.warName)

            val values = ContentValues().apply {
                put(COLUMN_WAR_NAME,item.warName)
                put(COLUMN_PATENT,item.patent)
                put(COLUMN_STATE,item.state)
            }

            db.insert(TABLE_NAME,null,values)
         }


    }

    fun updateState(military: military){

        val db = this.writableDatabase

        val value = ContentValues().apply {
            put(COLUMN_STATE,military.state)
        }

        db.update(
            TABLE_NAME,
            value,
            "${COLUMN_WAR_NAME} = ?",
            arrayOf(military.warName)
        )

        db.close()
    }

    fun getAllMilitaries():List<military>{

        val db = this.readableDatabase
        
        val list = mutableListOf<military>()

        val cursor: Cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ID, COLUMN_WAR_NAME, COLUMN_PATENT , COLUMN_STATE),
            null,
            null,
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    military(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PATENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WAR_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATE))
                    )
                )

                Log.i("teste",
                    "warName: ${list.last().warName} \n" +
                            "state: ${list.last().state}\n"
                )
            } while (cursor.moveToNext())
        }

        cursor.close()

        return list

    }


}


