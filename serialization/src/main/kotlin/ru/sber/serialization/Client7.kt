package ru.sber.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = Client7Deserializer::class)
data class Client7(
    val firstName: String,
    val lastName: String,
    val middleName: String,
)

class Client7Deserializer : JsonDeserializer<Client7>() {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Client7 {
        p0!!.nextTextValue()
        val value = p0.nextTextValue()!!.trim().split("\\s+".toRegex())
        return Client7(value[1], value[0], value[2])
    }
}
