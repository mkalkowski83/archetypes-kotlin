package archetypes.party

import archetypes.address.AddressUsage
import archetypes.address.EmailAddress
import archetypes.address.PhysicalType
import archetypes.address.TelecomAddress
import com.sumup.os.archetypes.UnitTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import archetypes.address.AddressProperties as IAddressProperties

internal class PartyAddressTest: UnitTest() {

    @Test
    fun `person has business address`() {
        // Given
        val emailBusiness = PartyAddress(EmailAddress("business@johndoe.com"), AddressProperties(listOf(AddressUsage.BUSINESS)))
        val emailHome = PartyAddress(EmailAddress("home@johndoe.com"), AddressProperties(listOf(AddressUsage.HOME)))
        val phoneBusiness = PartyAddress(TelecomAddress("+48", "0", "511", "123123", "", PhysicalType.MOBILE), AddressProperties(listOf(AddressUsage.BUSINESS)))

        // when
        val party = Person("John Doe", UniqueIdentifier("1234"), listOf(emailBusiness, emailHome, phoneBusiness))

        // then
        // Give me all the business addresses
        Assertions.assertEquals(party.addresses.filter { it.addressProperties.useAs.contains(AddressUsage.BUSINESS) }.size, 2)
    }

    internal class Person(
        val name: String,
        override val identifier: PartyIdentifier,
        override val addresses: List<PartyAddress>,
        override val description: String? = null,
    ): Party

    internal class AddressProperties(
        override val useAs: List<AddressUsage>
    ): IAddressProperties
}