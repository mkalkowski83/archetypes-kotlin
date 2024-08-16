package com.sumup.os.archetypes

import org.springframework.test.context.ActiveProfiles
import java.util.Locale

@ActiveProfiles("test")
internal abstract class AbstractTest {

    init {
        Locale.setDefault(Locale.of("en", "US"))
    }
}
