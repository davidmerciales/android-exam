package com.example.johndavidmerciales_android_exam.data.remote

import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.model.response.Results

interface ApiService {

    suspend fun getPersonList(params: GetPersonUseCaseRequest): Results
}