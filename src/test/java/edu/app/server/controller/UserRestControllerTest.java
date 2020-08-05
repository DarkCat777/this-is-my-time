package edu.app.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.app.server.model.Authority;
import edu.app.server.model.User;
import edu.app.server.repository.UserRepository;
import edu.app.server.service.UserService;
import edu.app.server.utils.Token;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestController userRestController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String adminToken;
    private String userToken;

    @BeforeEach
    void initUsersTest() {
        User adminUser = userService.getByUsername("erickdmh@outlook.es");
        User user = userService.getByUsername("erickdmh@gmail.com");
        adminToken = Token.getToken(adminUser);
        userToken = Token.getToken(user);
    }

    @Test
    public void testJWT() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content("{\"username\":\"erickdmh@outlook.es\",\"password\":\"983352035edmh\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPostRequestToUsersAndValidUserThenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all")
                .header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPostRequestToUsersAndInvalidUserThenCorrectResponse() throws Exception {
        User user = new User("", "983352035edmh", true);
        String userJSON = objectMapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/user/new")
                .header("Authorization", adminToken)
                .content(userJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
