package com.example.johndavidmerciales_android_exam.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale
fun String.toDate(): String{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)

    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}