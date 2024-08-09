package com.sumup.os.archetypes

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
internal class E2ETest : IntegrationTest() {
    @Autowired
    protected lateinit var mockMvc: MockMvc
}
