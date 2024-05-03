package com.example.johndavidmerciales_android_exam.domain.usecase

import com.example.johndavidmerciales_android_exam.domain.repository.PersonEntityRepository
import javax.inject.Inject

class DeleteAllPersonsUseCase @Inject constructor(
    private val personEntityRepository: PersonEntityRepository
) {

    suspend operator fun invoke(){
        personEntityRepository.deleteAllPersons()
    }
}