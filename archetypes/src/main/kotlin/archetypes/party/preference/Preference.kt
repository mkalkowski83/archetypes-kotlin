package archetypes.party.preference

data class Preference(
    val preferenceWeight: Quantity,
    val type: PreferenceType,
    val option: PreferenceOption?
) {
    fun getOptionName(): String = option?.name ?: ""
    fun getOptionDescription(): String = option?.description ?: ""
    fun getType(): PreferenceType = type
}