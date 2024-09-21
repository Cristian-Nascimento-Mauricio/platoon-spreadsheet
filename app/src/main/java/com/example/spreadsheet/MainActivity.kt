package com.example.spreadsheet

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spreadsheet.data.controllers.controllersMilitary

class MainActivity : AppCompatActivity() {

    var controllersMilitary:controllersMilitary = controllersMilitary(this)
    lateinit var adapter:Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val recylerView = findViewById<RecyclerView>(R.id.recyclerView)
        recylerView.layoutManager = LinearLayoutManager(this)


        adapter = Adapter( controllersMilitary)
        recylerView.adapter = adapter

    }

    fun reloadList(view: View) {
        if (::adapter.isInitialized)
            adapter.resetList()

    }




}