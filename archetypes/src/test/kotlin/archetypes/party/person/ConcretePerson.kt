package archetypes.party.person

import archetypes.party.PartyIdentifier
import archetypes.party.PartyRole
import archetypes.party.RegisteredIdentifier
import archetypes.party.address.EmailAddress
import archetypes.party.preference.Preference
import java.time.OffsetDateTime

class ConcretePerson(
    val pesel: String,
    private val registeredIdentifier: List<RegisteredIdentifier> = emptyList(),
    override val personName: PersonName,
    override val isoGender: ISOGender,
    override val dateOfBirth: OffsetDateTime? = null,
    override val otherPersonNames: List<PersonName> = listOf(),
    override val ethnicity: Ethnicity? = null,
    override val description: String? = null,
) : Person() {

    override fun getIdentifier(): PartyIdentifier = PartyIdentifier(pesel)

    override fun getRegisteredIdentifiers(): List<RegisteredIdentifier> = registeredIdentifier

    override fun getName(): String = "${personName.givenName} ${personName.familyName}"

    override fun getRoles(): List<PartyRole> {
        TODO("Not yet implemented")
    }

    override fun getPreferences(): List<Preference> {
        TODO("Not yet implemented")
    }

    fun getEmailAddresses(): List<EmailAddress> {
        return getAddresses()
            .filterIsInstance<EmailAddress>()
    }
}
