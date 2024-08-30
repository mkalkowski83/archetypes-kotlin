package archetypes.party

import archetypes.party.address.Address
import archetypes.party.address.AddressProperties
import archetypes.party.preference.Preference

abstract class Party {
    abstract val description: String?

    abstract fun getIdentifier(): PartyIdentifier

    abstract fun getRegisteredIdentifiers(): List<RegisteredIdentifier>

    abstract fun getName(): String

    abstract fun getAddresses(): List<Address>

    abstract fun getRoles(): List<PartyRole>

    abstract fun getPreferences(): List<Preference>

    abstract val addresses: List<Address>
    abstract val addressProperties: AddressProperties

    val authentications: MutableList<PartyAuthentication> = mutableListOf()
}
