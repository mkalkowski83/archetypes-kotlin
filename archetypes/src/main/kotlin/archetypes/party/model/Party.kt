package archetypes.party.model

import org.springframework.data.annotation.Id
import java.util.UUID

abstract class Party(
    @Id val id: PartyIdentifier = PartyIdentifier(UUID.randomUUID()),
    val description: String? = null,
)
