package archetypes.locale.model

import org.springframework.data.relational.core.mapping.Column

class ISOCountryCode(
    @Column("code")
    override val identifier: String,
    @Column("name")
    override val name: String,
    @Column("description")
    override val description: String? = null,
    @Column("alphabetic_three_character_code")
    val alphabeticThreeCharacterCode: String? = null,
    @Column("numeric_code")
    val numericCode: String? = null,
    @Column("official_name")
    val officialName: String? = null
): Locale