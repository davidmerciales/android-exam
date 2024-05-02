package com.example.johndavidmerciales_android_exam.data.remote

import android.util.Log
import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.model.response.Results
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.parametersOf
import java.util.Objects
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
): ApiService {
    override suspend fun getPersonList(params: GetPersonUseCaseRequest): Results {
        val response = httpClient.get("https://randomuser.me/api/?"){
            parameter("page", params.page)
            parameter("results", params.results)
            parameter("seed", params.seed)
        }

        return response.body<Results>()
    }
}