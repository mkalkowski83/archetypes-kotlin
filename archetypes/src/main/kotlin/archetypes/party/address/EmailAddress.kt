package archetypes.party.address
import java.time.OffsetDateTime

class EmailAddress(
    emailAddress: String,
    override val validFrom: OffsetDateTime? = null,
    override val validTo: OffsetDateTime? = null,
) : Address() {
    private val emailAddress: String = emailAddress.lowercase()

    init {
        require(emailAddress.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$".toRegex(RegexOption.IGNORE_CASE))) { "Invalid email address" }
    }

    override fun getAddress(): String = emailAddress
}
