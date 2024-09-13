package archetypes.party

import archetypes.party.address.Address
import archetypes.party.address.AddressProperties

data class PartyAddress(
    val address: Address,
    val properties: AddressProperties,
)
