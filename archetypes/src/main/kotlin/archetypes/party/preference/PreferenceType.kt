package archetypes.party.preference

import archetypes.party.UniqueIdentifier

data class PreferenceType(
    val name: String,
    val description: String,
    val thing: UniqueIdentifier,
) {
    fun getOptions(): List<PreferenceOption> = emptyList()

    fun getUniqueIdentifierForThing(): UniqueIdentifier = thing
}
