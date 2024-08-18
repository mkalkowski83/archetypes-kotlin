package archetypes.person

import archetypes.party.Party
import archetypes.party.PartyAddress
import archetypes.party.PartyIdentifier
import java.time.OffsetDateTime

class Person(
    val dateOfBirth: OffsetDateTime? = null,
    val personName: PersonName,
    val othersPersonNames: List<PersonName>? = null,
    val gender: ISOGender,
    override val identifier: PartyIdentifier,
    override val addresses: List<PartyAddress>,
    override val description: String? = null,
) : Party
