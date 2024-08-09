package com.sumup.os.apptemplate

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sumup.os.utils.serializer.service.JacksonJsonSerializer
import org.springframework.test.context.ActiveProfiles
import java.util.Locale

@ActiveProfiles("test")
internal abstract class AbstractTest {
    protected val jsonSerializer = JacksonJsonSerializer(jacksonObjectMapper().registerModule(JavaTimeModule()))

    companion object {
        const val DEFAULT_MERCHANT_CODE = "ABCDEFGH"
    }

    init {
        Locale.setDefault(Locale.of("en", "US"))
    }
}
