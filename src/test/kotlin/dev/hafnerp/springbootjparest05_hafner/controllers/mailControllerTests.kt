package dev.hafnerp.springbootjparest05_hafner.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(MailController::class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MailControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

//    @Test
//    fun getAllMailsNoFilter() {
//        mockMvc
//            .perform(get("/api/mails/").accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$").isArray)
//    }
}
