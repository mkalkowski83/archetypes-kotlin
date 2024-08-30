package archetypes.party.address

import java.time.OffsetDateTime

abstract class Address {
    abstract val validFrom: OffsetDateTime?
    abstract val validTo: OffsetDateTime?

    abstract fun getAddress(): String
}
