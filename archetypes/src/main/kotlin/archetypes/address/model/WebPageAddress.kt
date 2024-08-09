package archetypes.address.model

import org.springframework.data.relational.core.mapping.Column
import java.time.OffsetDateTime

class WebPageAddress(
    @Column("web_page")
    private val value: String,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null
): Address {
    init {
        require(value.matches("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$".toRegex(RegexOption.IGNORE_CASE))) { "Invalid web page address" }
    }

    fun getValue(): String {
        return value.lowercase()
    }
}