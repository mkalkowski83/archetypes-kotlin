package archetypes.party.address

import java.time.OffsetDateTime

interface Address {
    val validFrom: OffsetDateTime?
    val validTo: OffsetDateTime?
}
