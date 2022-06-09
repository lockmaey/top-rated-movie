package com.example.movies.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun loadJsonFile(context: Context, fileName: String): String {

    val bufferedReader = BufferedReader(InputStreamReader(context.assets.open(fileName)))
    val stringBuilder = StringBuilder()
    var line: String? = bufferedReader.readLine()
    while (line != null) {
        stringBuilder.append(line).append("\n")
        line = bufferedReader.readLine()
    }
    bufferedReader.close()

    return stringBuilder.toString()
}
