package com.example.spreadsheet

import android.annotation.SuppressLint
import android.icu.util.LocaleData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spreadsheet.data.controllers.controllersMilitary
import com.example.spreadsheet.util.militariesState
import com.example.spreadsheet.util.military
import java.time.LocalDate
import java.time.LocalTime

class Adapter( private val controllersMilitary: controllersMilitary) : RecyclerView.Adapter<Adapter.myAdapter>() {

    private val list:List<military> = controllersMilitary.getAllMilitaries()

    class myAdapter(itemView: View) : RecyclerView.ViewHolder(itemView){
        val button:Button = itemView.findViewById(R.id.button)
        val textPatent:TextView = itemView.findViewById(R.id.textPatent)
        val textWarName:TextView = itemView.findViewById(R.id.textWarName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component,parent,false)

        return myAdapter(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: myAdapter, position: Int) {

        holder.textPatent.text = list.get(position).patent
        holder.textWarName.text = list.get(position).warName
        holder.button.text = list.get(position).state

        holder.button.setTextColor(ContextCompat.getColor(holder.itemView.context,
            defineColorForEachMilitarState(list.get(position).state)
        ))

        holder.button.setOnClickListener(){

            val builder:AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context)

            val listWhich = convertMapToList(militariesState())

            builder
                .setTitle("Tirada de falta")
                .setSingleChoiceItems(
                    listWhich,
                    -1
                ) { dialog, which ->
                    dialog.dismiss()
                    list.get(position).state = listWhich.get(which)
                    controllersMilitary.updateState(list.get(position))
                    notifyItemChanged(position)

                }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

    }

    fun resetList(){
        list.forEach { it ->
            it.state = "definir"
            controllersMilitary.updateState(it)
        }
        notifyDataSetChanged()

    }
    private fun convertMapToList(map:Map<String,Int>):Array<String>{
        val list: MutableList<String> = mutableListOf()

        map.forEach{
            list.add(it.key)

        }

        return list.toTypedArray()

    }
    private fun defineColorForEachMilitarState(state: String): Int {
        return militariesState().getOrDefault(state,R.color.CornflowerBlue)

    }

    private fun convertDateToStringDate():String{

        //class LocalDate and LocalTime using to get date now and time now
        val date = LocalDate.now()
        val time = LocalTime.now()

        var hour = String.format("%02d",time.hour)
        var minute = String.format("%02d",time.minute)

        var month = String.format("%02d",date.monthValue)
        var day = String.format("%02d",date.dayOfMonth)
        //get only last number of year
        var year = date.year % 2000

        return "${hour}:${minute} - ${day}/${month}/${year}"

    }

    fun convertListToString():String{

        var text:String = "*Falta do Pelcom*\n \n*Data:* ${convertDateToStringDate()}  \n"

        list.forEach { it ->
            if (it.state != "definir")
                text = text + "\n${it.patent} ${it.warName}: *${it.state}*"
        }


        return text

    }

}

