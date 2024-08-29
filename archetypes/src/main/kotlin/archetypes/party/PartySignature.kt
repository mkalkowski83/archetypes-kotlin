package archetypes.party

import java.time.OffsetDateTime

data class PartySignature(
    val whenCreated: OffsetDateTime,
    val reason: String? = null,
    val partyIdentifier: PartyIdentifier
) {
    fun getPartyIdentifier(): PartyIdentifier = partyIdentifier
    fun getAuthentication(): PartyAuthentication? = null
}