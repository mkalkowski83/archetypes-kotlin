package archetypes.party

import archetypes.party.address.Address
import archetypes.party.address.AddressProperties
import archetypes.party.address.AddressUsage
import archetypes.party.preference.Preference

abstract class Party {
    abstract val description: String?

    abstract fun getIdentifier(): PartyIdentifier

    abstract fun getRegisteredIdentifiers(): List<RegisteredIdentifier>

    abstract fun getName(): String

    fun getAddresses(): List<Address> = addresses.toList()

    abstract fun getRoles(): List<PartyRole>

    abstract fun getPreferences(): List<Preference>

    private val addresses: MutableList<Address> = mutableListOf()
    private val partyAddresses: MutableList<PartyAddress> = mutableListOf()

    val authentications: MutableList<PartyAuthentication> = mutableListOf()

    fun addAddress(
        address: Address,
        properties: AddressProperties,
    ) {
        addresses.add(address)
        partyAddresses.add(PartyAddress(address, properties))
    }

    fun removeAddress(partyAddress: PartyAddress) {
        addresses.remove(partyAddress.address)
        partyAddresses.remove(partyAddress)
    }

    fun getAddressesByUse(use: AddressUsage): List<Address> {
        return partyAddresses
            .filter { it.properties.use.contains(use) }
            .map { it.address }
    }
}
