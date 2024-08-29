package archetypes.party.person

import java.time.OffsetDateTime

data class PersonName(
    val prefix: List<String> = listOf(),
    val givenName: String? = null,
    val middleName: List<String> = listOf(),
    val familyName: String,
    val preferredName: String? = null,
    val suffix: List<String> = listOf(),
    val use: List<String> = listOf(),
    val validFrom: OffsetDateTime? = null,
    val validTo: OffsetDateTime? = null
)
