package archetypes.address

import archetypes.geography.Locale
import java.time.OffsetDateTime

class GeographicAddress(
    val addressLine: List<String>,
    val city: String,
    val regionOrState: String,
    val zipOrPostalCode: String,
    val country: Locale,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null
): Address