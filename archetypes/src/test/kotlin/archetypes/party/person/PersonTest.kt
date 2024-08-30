package archetypes.party.person

import archetypes.UnitTest
import archetypes.party.address.AddressUsage
import archetypes.party.address.EmailAddress
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

internal class PersonTest : UnitTest() {
    @Test
    fun `person has email addresses`() {
        // Given
        val emailBusiness = EmailAddress("business@johndoe.com")
        val emailHome = EmailAddress("home@johndoe.com")
        val person =
            ConcretePerson(
                pesel = "1234567890",
                dateOfBirth = OffsetDateTime.parse("1990-01-01T00:00:00Z"),
                personName = PersonName(listOf(NamePrefix.MR), "John", familyName = "Doe"),
                isoGender = ISOGender.MALE,
                description = "Test person",
            )
        person.addAddress(emailBusiness, ConcreteAddressProperties(listOf(AddressUsage.BUSINESS)))
        person.addAddress(emailHome, ConcreteAddressProperties(listOf(AddressUsage.HOME, AddressUsage.BUSINESS)))

        // When
        val emailAddresses = person.getEmailAddresses()

        // Then
        Assertions.assertEquals(2, emailAddresses.size)
        Assertions.assertTrue(emailAddresses.contains(emailHome))
        Assertions.assertTrue(emailAddresses.contains(emailBusiness))

        // give me only home addresses
        val homeAddresses = person.getAddressesByUse(AddressUsage.HOME)
        Assertions.assertEquals(1, homeAddresses.size)
        Assertions.assertTrue(homeAddresses.contains(emailHome))
    }
}
