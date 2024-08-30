package archetypes.party

data class PartyIdentifier(val identifier: String) : UniqueIdentifier {
    override fun getIdentifier(): String = identifier
}
