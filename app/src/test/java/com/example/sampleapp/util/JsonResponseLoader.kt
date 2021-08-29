package com.example.sampleapp.util

import java.io.File

import com.squareup.moshi.Moshi


object JsonResponseLoader {
    private const val JSON_FILE_PATH =
        "../app/src/test/java/com/example/sampleapp/mocks/json/"

    fun loadJson(name: String) = File(JSON_FILE_PATH + name).readText(Charsets.UTF_8)

    inline fun <reified T> loadJsonToPojo(name: String) =
        Moshi
            .Builder()
            .build()
            .adapter(T::class.java)
            .fromJson(loadJson(name))
}