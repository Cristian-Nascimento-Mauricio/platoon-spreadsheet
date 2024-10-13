package com.example.spreadsheet.util

import com.example.spreadsheet.R

//Add here miliaries of your platoon
fun pelList():List<military>{
    return arrayListOf(
        military("2ºTEN","Fernandes"),
        military("3ºSGT","Leal"),
        military("3ºSGT","Menezes"),
        military("3ºSGT","Camargo"),
        military("CB","Campos"),
        military("CB","Lima"),
        military("CB","De Souza"),
        military("CB","Bento"),
        military("SD","Amaro"),
        military("SD.EV","Cristian"),
        military("SD.EV","Luan Castro"),
        military("SD.EV","Boleli"),
        military("SD.EV","Luiz Miguel"),
        military("SD.EV","Gustavo")

        )
}

fun militariesState():Map<String,Int> {
    return mapOf(
        "Presente" to R.color.Green,
        "Ausente" to R.color.Red,
        "Dispensado" to R.color.OrangeRed,
        "Férias" to R.color.OrangeRed,
        "Em serviço" to R.color.Yellow,
        "Saindo de serviço" to R.color.Yellow,
        "Em missão" to R.color.Yellow,
        "Baixado" to R.color.Yellow,
        "Estágio/Curso" to R.color.Yellow,
        "Faxina" to R.color.Yellow

    )

}


