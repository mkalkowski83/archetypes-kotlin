package archetypes.address.model

import org.springframework.data.relational.core.mapping.Column
import java.time.OffsetDateTime

class TelecomAddress(
    @Column("country_code")
    private val countryCode: String,
    @Column("national_direct_dialing_prefix")
    private val nationalDirectDialingPrefix: String,
    @Column("area_code")
    private val areaCode: String,
    @Column("number")
    private val number: String,
    @Column("extension")
    private val extension: String,
    @Column("physical_type")
    private val physicalType: PhysicalType,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null
): Address