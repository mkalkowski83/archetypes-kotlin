package archetypes.person

import archetypes.UnitTest
import archetypes.address.AddressUsage
import archetypes.address.EmailAddress
import archetypes.address.PhysicalType
import archetypes.address.TelecomAddress
import archetypes.party.PartyAddress
import archetypes.party.UniqueIdentifier
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

internal class PersonTest : UnitTest() {
    @Test
    fun `person has business address`() {
        // Given
        val emailBusiness =
            PartyAddress(EmailAddress("business@johndoe.com"), AddressProperties(listOf(AddressUsage.BUSINESS)))
        val emailHome = PartyAddress(EmailAddress("home@johndoe.com"), AddressProperties(listOf(AddressUsage.HOME)))
        val phoneBusiness = PartyAddress(
            TelecomAddress("+48", "0", "511", "123123", "", PhysicalType.MOBILE),
            AddressProperties(listOf(AddressUsage.BUSINESS))
        )

        // when
        val person =
            Person(
                dateOfBirth = OffsetDateTime.parse("1980-03-03T00:00:00+00:00"),
                personName = PersonName(listOf(NamePrefix.MR), listOf("John"), familyName = "Doe"),
                identifier = UniqueIdentifier("1234"),
                addresses = listOf(emailBusiness, emailHome, phoneBusiness),
                gender = ISOGender.FEMALE,
            )

        // then
        // Give me all the business addresses
        val addresses = person.addresses.filter { it.addressProperties.useAs.contains(AddressUsage.BUSINESS) }
        Assertions.assertEquals(addresses.size, 2)
        // Give me the Email address
        Assertions.assertEquals(addresses[0].address, emailBusiness.address)
    }

    internal class AddressProperties(
        override val useAs: List<AddressUsage>,
    ) : archetypes.address.AddressProperties
}