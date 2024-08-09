package archetypes.party.model

import java.util.UUID

data class PartyIdentifier(val id: UUID) {
    companion object {
        fun fromString(id: String): PartyIdentifier {
            return PartyIdentifier(UUID.fromString(id))
        }
    }

    override fun toString(): String {
        return id.toString()
    }
}