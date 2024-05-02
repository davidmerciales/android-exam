package com.example.johndavidmerciales_android_exam.domain.repository

import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.model.response.Results

interface PersonRepository {

    suspend fun getPersonList(params: GetPersonUseCaseRequest): Results
}