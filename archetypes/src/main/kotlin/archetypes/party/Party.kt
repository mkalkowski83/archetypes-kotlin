package archetypes.party

interface Party {
    val identifier: PartyIdentifier
    val addresses: List<PartyAddress>
    val description: String?
}
