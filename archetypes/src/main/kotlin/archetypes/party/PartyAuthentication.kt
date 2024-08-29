package archetypes.party

data class PartyAuthentication(
    val partySignature: PartySignature,
    val authentication: String
)
