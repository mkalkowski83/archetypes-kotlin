package archetypes.address.model
import org.springframework.data.relational.core.mapping.Column
import java.time.OffsetDateTime

class EmailAddress(
    @Column("email")
    private val value: String,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null
): Address {
    init {
        require(value.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$".toRegex(RegexOption.IGNORE_CASE))) { "Invalid email address" }
    }

    fun getValue(): String {
        return value.lowercase()
    }
}