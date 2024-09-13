package archetypes.party.organization

import archetypes.UnitTest
import archetypes.party.address.AddressUsage
import archetypes.party.address.EmailAddress
import archetypes.party.address.GeographicAddress
import archetypes.party.geography.Locale
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class OrganizationTest : UnitTest() {
    @Test
    fun `organization has name and address`() {
        // Given
        val contactEmail = EmailAddress("info@sumup.com")
        val warsawOfficeEmail = EmailAddress("warsaw@sumup.com")
        val addressWarsaw =
            GeographicAddress(
                addressLine = listOf("Prosta 20"),
                city = "Warsaw",
                country = Locale("pL", "Poland"),
                regionOrState = "Mazowieckie",
                zipOrPostCode = "00-850",
            )
        val organization =
            ConcreteOrganization(
                organizationName = OrganizationName("Sumup Inc."),
                description = "Sumup Services GmbH Sp. z o.o. Oddział w Polsce\n",
            )
        organization.addAddress(contactEmail, OrganizationAddressProperties(listOf(AddressUsage.BUSINESS)))
        organization.addAddress(addressWarsaw, OrganizationAddressProperties(listOf(AddressUsage.BUSINESS)))
        organization.addAddress(warsawOfficeEmail, OrganizationAddressProperties(listOf(AddressUsage.BUSINESS)))

        // When/Then
        Assertions.assertSame("Sumup Inc.", organization.getName())
    }
}
