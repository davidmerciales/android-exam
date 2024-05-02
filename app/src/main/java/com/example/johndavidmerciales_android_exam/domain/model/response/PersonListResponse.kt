package com.example.johndavidmerciales_android_exam.domain.model.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class Results(
    val results: List<Result>,
    val info: Info
)

@Serializable
data class Result(
    val gender: String,
    val name: Name,
    @Contextual
    val location: BaseLocation,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val id: Id,
    val picture: Picture,
    val nat: String
)

@Serializable
data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)
@Serializable
data class Name(
    val title: String,
    val first: String,
    val last: String,
)

@Serializable(with = BaseItemSerializer::class)
sealed class BaseLocation {
    abstract val street: Street
    abstract val city: String
    abstract val state: String
    abstract val country: String
    abstract val coordinates: Coordinates
    abstract val timezone: Timezone
}
@Serializable
data class LocationString(
    override val street: Street,
    override val city: String,
    override val state: String,
    override val country: String,
    val postcode: String,
    override val coordinates: Coordinates,
    override val timezone: Timezone,
) : BaseLocation()

@Serializable
data class LocationInt(
    override val street: Street,
    override val city: String,
    override val state: String,
    override val country: String,
    val postcode: Int,
    override val coordinates: Coordinates,
    override val timezone: Timezone,
) : BaseLocation()

@Serializable
data class Street(
    val number: Int,
    val name: String,
)

@Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String,
)
@Serializable
data class Timezone(
    val offset: String,
    val description: String,
)

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
)

@Serializable
data class Dob(
    val date: String,
    val age: Int,
)

@Serializable
data class Registered(
    val date: String,
    val age: Int,
)

@Serializable
data class Id(
    val name: String,
    val value: String?,
)

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String,
)


object BaseItemSerializer : JsonContentPolymorphicSerializer<BaseLocation>(BaseLocation::class) {
    override fun selectDeserializer(
        element: JsonElement
    ): DeserializationStrategy<BaseLocation> {

        return when (val type = element.jsonObject["country"]?.jsonPrimitive?.toString()) {
            "Canada"    -> LocationString.serializer()
            "Australia"    -> LocationString.serializer()
            "Netherlands"    -> LocationString.serializer()
            "United Kingdom"    -> LocationString.serializer()
            else -> LocationInt.serializer()
        }
    }
}

