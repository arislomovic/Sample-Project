package com.example.sampleapp.extensions

import kotlin.math.pow
import kotlin.math.sqrt

val FloatArray.standardDeviation
    get(): Double {

        val mean = average()

        val standardDeviation = sumOf { num -> (num - mean).pow(2.0) }

        return sqrt(standardDeviation / size)
    }

fun Number.celsiusToFahrenheit() = toFloat() * 1.8f + 32
