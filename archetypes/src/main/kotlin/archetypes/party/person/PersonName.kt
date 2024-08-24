package archetypes.party.person

class PersonName(
    val prefix: List<NamePrefix>? = null,
    val givenName: List<String>? = null,
    val middleName: List<String>? = null,
    val familyName: String,
    val preferredName: List<String>? = null,
    val suffix: List<String>? = null,
    val use: List<String>? = null,
    val validFrom: String? = null,
    val validTo: String? = null,
)
