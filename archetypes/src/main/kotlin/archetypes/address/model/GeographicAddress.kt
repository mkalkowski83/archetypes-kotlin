package archetypes.address.model

import archetypes.locale.model.Locale
import org.springframework.data.relational.core.mapping.Column
import java.time.OffsetDateTime

class GeographicAddress(
    @Column("lines")
    val addressLine: List<String>,
    @Column("city")
    val city: String,
    @Column("region_or_state")
    val regionOrState: String,
    @Column("zip_or_postal_code")
    val zipOrPostalCode: String,
    @Column("country")
    val country: Locale,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null
): Address