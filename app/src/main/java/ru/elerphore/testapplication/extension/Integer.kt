package ru.elerphore.testapplication.extension

fun Int.toPercentage() = if(this > 100)  "100 %"  else "$this %"