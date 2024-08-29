package archetypes.party.address

import archetypes.party.geography.Locale
import java.time.OffsetDateTime

class GeographicAddress(
    val addressLine: List<String>,
    val city: String,
    val regionOrState: String,
    val zipOrPostCode: String,
    val country: Locale,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null
) : Address() {
    override fun getAddress(): String =
        "${addressLine.joinToString(", ")}, $city, $regionOrState $zipOrPostCode, ${country.name}"

    fun getCountry(): Locale = country
}