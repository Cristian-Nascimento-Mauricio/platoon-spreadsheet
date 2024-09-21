package com.example.spreadsheet.util

import com.example.spreadsheet.R

//Add here miliaries of your platoon
fun pelList():List<military>{
    return arrayListOf(


        )
}

fun militariesState():Map<String,Int> {
    return mapOf(
        "Presente" to R.color.Green,
        "Ausente" to R.color.Red,
        "Dispensado" to R.color.OrangeRed,
        "Em serviço" to R.color.Yellow,
        "Saindo de serviço" to R.color.Yellow,
        "Em missão" to R.color.Yellow,
        "Baixado" to R.color.Yellow,
        "Estágio/Curso" to R.color.Yellow,
        "Faxina" to R.color.Yellow

    )

}


