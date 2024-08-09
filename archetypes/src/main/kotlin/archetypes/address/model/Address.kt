package archetypes.address.model

import java.time.OffsetDateTime

interface Address {
    val validFrom: OffsetDateTime?
    val validTo: OffsetDateTime?
}