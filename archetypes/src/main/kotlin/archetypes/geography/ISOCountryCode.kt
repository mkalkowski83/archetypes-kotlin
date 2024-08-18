package archetypes.geography

class ISOCountryCode(
    override val identifier: String,
    override val name: String,
    override val description: String? = null,
    val alphabeticThreeCharacterCode: String? = null,
    val numericCode: String? = null,
    val officialName: String? = null,
) : Locale
