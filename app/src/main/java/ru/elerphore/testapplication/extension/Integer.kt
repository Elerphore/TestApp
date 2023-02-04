package ru.elerphore.testapplication.extension

fun Int.toPercentage() = "$this %"

fun Long.Companion.generateSecondsInRange(range: IntRange = (5..25)): Long = (range.random() * 1000).toLong()