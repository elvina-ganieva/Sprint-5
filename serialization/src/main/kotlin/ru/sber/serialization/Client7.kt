package ru.sber.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = ClientDeserializer::class)
data class Client7(
    val firstName: String,
    val lastName: String,
    val middleName: String,
)

class ClientDeserializer: JsonDeserializer<Client7>() {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Client7 {
        p0!!.nextTextValue()
        val value = p0.nextTextValue()?.split("\\s+".toRegex())
        return Client7(value!![1], value[0], value[2])
    }
}
