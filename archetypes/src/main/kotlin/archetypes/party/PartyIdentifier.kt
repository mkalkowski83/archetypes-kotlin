package archetypes.party

data class PartyIdentifier(private val identifier: String) : UniqueIdentifier {
    override fun getIdentifier(): String = identifier
}
