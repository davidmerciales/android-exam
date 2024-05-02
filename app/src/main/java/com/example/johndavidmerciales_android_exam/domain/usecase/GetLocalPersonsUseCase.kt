package com.example.johndavidmerciales_android_exam.domain.usecase

import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.Person
import com.example.johndavidmerciales_android_exam.domain.repository.PersonEntityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalPersonsUseCase @Inject constructor(
    private val personEntityRepository: PersonEntityRepository
) {
    suspend operator fun invoke(): Flow<List<PersonEntity>>{
        return personEntityRepository.getPersons()
    }
}
//fun List<PersonEntity>.mapToPerson(): Flow<List<Person>> {
//    return map {
//        with(it) {
//            Person(
//                title = title,
//                firstName = firstName,
//                lastName = lastName,
//                gender = gender,
//                birthday = birthday,
//                age = age,
//                emailAddress = emailAddress,
//                mobileNumber = mobileNumber,
//                address = address
//            )
//        }
//    }
//}