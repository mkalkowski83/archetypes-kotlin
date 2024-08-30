package archetypes.party.organization

import archetypes.party.Party

abstract class Organization : Party() {
    abstract val organizationName: OrganizationName
    abstract val otherOrganizationNames: List<OrganizationName>

    abstract fun getOrganizationName(): OrganizationName

    abstract fun getOtherOrganizationNames(): List<OrganizationName>
}
