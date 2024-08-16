package archetypes.party

import java.util.UUID

abstract class Party(
    val id: PartyIdentifier = PartyIdentifier(UUID.randomUUID()),
    val description: String? = null,
)
