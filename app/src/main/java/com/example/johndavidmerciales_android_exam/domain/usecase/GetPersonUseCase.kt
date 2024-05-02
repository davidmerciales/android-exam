package com.example.johndavidmerciales_android_exam.domain.usecase

import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.model.response.Results
import com.example.johndavidmerciales_android_exam.domain.repository.PersonRepository
import com.example.johndavidmerciales_android_exam.domain.utils.Either
import com.example.johndavidmerciales_android_exam.domain.utils.Failure
import com.example.johndavidmerciales_android_exam.domain.utils.Success
import javax.inject.Inject

class GetPersonUseCase @Inject constructor(
    private val personRepository: PersonRepository
): UseCase<Results, GetPersonUseCaseRequest>()  {
    override suspend fun run(params: GetPersonUseCaseRequest): Either<Exception, Results> {
        return try {
            val response = personRepository.getPersonList(params)
            Success(response)
        }catch (e: Exception){
            Failure(e)
        }
    }
}