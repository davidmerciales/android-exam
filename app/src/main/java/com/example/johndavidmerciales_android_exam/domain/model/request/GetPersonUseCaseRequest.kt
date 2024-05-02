package com.example.johndavidmerciales_android_exam.domain.model.request

data class GetPersonUseCaseRequest(
    var page: String,
    val results: String,
    val seed: String,)
