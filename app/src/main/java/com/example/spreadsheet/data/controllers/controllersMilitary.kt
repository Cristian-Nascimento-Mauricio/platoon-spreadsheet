package com.example.spreadsheet.data.controllers

import android.content.ContentValues
import android.content.Context
import com.example.spreadsheet.data.database.myDatabase
import com.example.spreadsheet.util.military

class controllersMilitary( context: Context) {

    val database:myDatabase = myDatabase(context)


    fun updateState(military: military){
        database.updateState(military)
    }

    fun getAllMilitaries():List<military>{
        return database.getAllMilitaries()
    }

}