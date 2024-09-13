package archetypes.party.address

import java.time.OffsetDateTime

class WebPageAddress(
    private val url: String,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null,
) : Address() {
    init {
        require(url.matches("^(https?|ftp)://[^\\s/$.?#].\\S*$".toRegex(RegexOption.IGNORE_CASE))) { "Invalid web page address" }
    }

    override fun getAddress(): String = url
}
