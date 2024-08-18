package archetypes.address

import java.time.OffsetDateTime

class WebPageAddress(
    val webPageAddress: String,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null,
) : Address {
    init {
        require(webPageAddress.matches("^(https?|ftp)://[^\\s/$.?#].\\S*$".toRegex(RegexOption.IGNORE_CASE))) { "Invalid web page address" }
    }
}
