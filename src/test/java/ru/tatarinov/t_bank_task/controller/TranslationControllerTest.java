package ru.tatarinov.t_bank_task.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.tatarinov.t_bank_task.repository.RequestRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TranslationControllerTest {
    @MockBean
    private RequestRepository requestRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    public void testTranslate() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        String expectedResponse = "hello world";

        mockMvc.perform(post("/translate")
                        .param("text", "здравствуй мир")
                        .param("source", "ru")
                        .param("target", "en"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }
}