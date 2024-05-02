package com.example.johndavidmerciales_android_exam.domain.usecase

import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.repository.PersonEntityRepository
import javax.inject.Inject

class InsertPersonUseCase @Inject constructor(
    private val personEntityRepository: PersonEntityRepository
){
    suspend operator fun invoke(persons: List<PersonEntity>) {
        personEntityRepository.insertPersons(persons)
    }
}