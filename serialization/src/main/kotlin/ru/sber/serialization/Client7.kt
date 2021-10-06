package ru.sber.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize


@JsonDeserialize(using = Client7Deserializer::class)
data class Client7(
    val firstName: String,
    val lastName: String,
    val middleName: String,
)

class Client7Deserializer : JsonDeserializer<Client7>() {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Client7 {
        val codec: ObjectCodec = p0!!.codec
        val node = codec.readTree<JsonNode>(p0)
        val str = node.findValue("client").textValue().split("\\s+".toRegex())
        return Client7(str[1], str[0], str[2])
    }
}
