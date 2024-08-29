package archetypes.party.geography

class ISOCountryCode(
    identifier: String,
    name: String,
    description: String? = null,
    val alphabeticThreeCharacterCode: String,
    val numericCode: String,
    val officialName: String
) : Locale(identifier, name, description) {
    fun getShortName(): String = name
    fun getAlphabeticTwoCharacterCode(): String = identifier
}

