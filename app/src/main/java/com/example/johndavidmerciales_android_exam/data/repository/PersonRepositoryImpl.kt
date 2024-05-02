package com.example.johndavidmerciales_android_exam.data.repository

import com.example.johndavidmerciales_android_exam.data.remote.ApiService
import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): PersonRepository {
    override suspend fun getPersonList(params: GetPersonUseCaseRequest) = apiService.getPersonList(params)

}