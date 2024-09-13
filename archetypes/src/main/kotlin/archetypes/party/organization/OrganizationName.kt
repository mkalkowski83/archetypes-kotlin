package archetypes.party.organization

import java.time.OffsetDateTime

class OrganizationName(
    val name: String,
    val use: String? = null,
    val validFrom: OffsetDateTime? = null,
    val validTo: OffsetDateTime? = null,
)
