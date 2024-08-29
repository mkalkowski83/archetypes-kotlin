package archetypes.party

import java.time.OffsetDateTime

data class RegisteredIdentifier(
    val identifier: String,
    val type: String? = null,
    val validFrom : OffsetDateTime? = null,
    val validTo : OffsetDateTime? = null,
    val registrationAuthority: String? = null
)