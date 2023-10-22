package com.egor.gavrilovbanking.integrationtests;

import com.egor.gavrilovbanking.dto.LoginDTO;
import com.egor.gavrilovbanking.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
public class RestIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private static final UserDTO userDTO = UserDTO.builder()
            .tel(88005553535L)
            .email("1@1.ru")
            .password("qwerty")
            .username("test1")
            .build();

    private static final UserDTO userDTO2 = UserDTO.builder()
            .tel(88005556666L)
            .email("2@1.ru")
            .password("qwerty")
            .username("test2")
            .build();

    private static final LoginDTO loginDTO = LoginDTO.builder()
            .username(userDTO.getUsername())
            .password(userDTO.getPassword())
            .build();

    @Test
    public void testBankingRestApp() throws Exception {
        String token;
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/user/reg")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/user/reg")
                        .content(objectMapper.writeValueAsString(userDTO2))
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        token = mockMvc.perform(get("/user/login")
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(post("/user/banking/addMoney")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("amount", "300000")
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        mockMvc.perform(post("/user/banking/getMoney")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("amount", "150000")
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        mockMvc.perform(post("/user/banking/getMoney")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("amount", "30000000")
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());

        mockMvc.perform(post("/user/banking/transferMoney")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("amount", "30000")
                        .param("reciver", "test2")
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        mockMvc.perform(post("/user/banking/transferMoney")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("amount", "30000")
                        .param("reciver", "test3")
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());

        mockMvc.perform(post("/user/banking/transferMoney")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("amount", "3000000")
                        .param("reciver", "test2")
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());

        mockMvc.perform(get("/user/banking/balance")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .characterEncoding(Charset.defaultCharset())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string("120000"));
    }
}