package archetypes.party.organization

import archetypes.party.PartyIdentifier
import archetypes.party.PartyRole
import archetypes.party.RegisteredIdentifier
import archetypes.party.preference.Preference

class ConcreteOrganization(
    override val organizationName: OrganizationName,
    override val description: String?,
    override val otherOrganizationNames: List<OrganizationName>? = null,
): Company() {
    override fun getIdentifier(): PartyIdentifier {
        TODO("Not yet implemented")
    }

    override fun getRegisteredIdentifiers(): List<RegisteredIdentifier> {
        TODO("Not yet implemented")
    }

    override fun getName(): String = organizationName.name

    override fun getRoles(): List<PartyRole> {
        TODO("Not yet implemented")
    }

    override fun getPreferences(): List<Preference> {
        TODO("Not yet implemented")
    }
}