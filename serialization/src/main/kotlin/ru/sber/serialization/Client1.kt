package ru.sber.serialization

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonRawValue
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class Client1(
    @JsonAlias("FIRSTNAME")
    val firstName: String,
    @JsonAlias("LASTNAME")
    val lastName: String,
    @JsonAlias("MIDDLENAME")
    val middleName: String?,
    @JsonAlias("PASSPORTNUMBER")
    val passportNumber: String,
    @JsonAlias("PASSPORTSERIAL")
    val passportSerial: String,
    @JsonAlias("BIRTHDATE")
    val birthDate: LocalDate
)
