package archetypes.party.person

import archetypes.party.Party
import java.time.OffsetDateTime

abstract class Person : Party() {
    abstract val dateOfBirth: OffsetDateTime?
    abstract val personName: PersonName
    abstract val otherPersonNames: List<PersonName>
    abstract val ethnicity: Ethnicity?
    abstract val isoGender: ISOGender

    abstract fun getPersonName(): PersonName

    abstract fun getOtherPersonNames(): List<PersonName>

    abstract fun getEthnicity(): Ethnicity?

    abstract fun getISOGender(): ISOGender
}
