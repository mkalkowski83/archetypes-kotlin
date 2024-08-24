package archetypes.party.person

import archetypes.UnitTest
import archetypes.party.PartyAddress
import archetypes.party.UniqueIdentifier
import archetypes.party.address.AddressUsage
import archetypes.party.address.EmailAddress
import archetypes.party.address.PhysicalType
import archetypes.party.address.TelecomAddress
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PersonTest : UnitTest() {
    @Test
    fun `person has business address`() {
        // Given
        val emailBusiness =
            PartyAddress(EmailAddress("business@johndoe.com"), AddressProperties(listOf(AddressUsage.BUSINESS)))
        val emailHome = PartyAddress(EmailAddress("home@johndoe.com"), AddressProperties(listOf(AddressUsage.HOME)))
        val phoneBusiness =
            PartyAddress(
                TelecomAddress("+48", "0", "511", "123123", "", PhysicalType.MOBILE),
                AddressProperties(listOf(AddressUsage.BUSINESS)),
            )
        //add named constructor
        // when
        val person =
            Person(
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
    ) : archetypes.party.address.AddressProperties
}
