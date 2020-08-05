package edu.app.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.app.server.model.Authority;
import edu.app.server.model.User;
import edu.app.server.service.UserService;
import edu.app.server.utils.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupTaskRestControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String adminToken;

    @BeforeEach
    void initUsersTest() {
        User adminUser = userService.getByUsername("erickdmh@outlook.es");
        adminToken = Token.getToken(adminUser);
    }

    @Test
    public void whenGetRequestAllGroupTasks() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.get("/groupTask/all")
                .header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPostRequestGetGroupTask() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.post("/groupTask/get/1")
                .header("Authorization", adminToken))
                .andExpect(status().isOk());
    }
}
