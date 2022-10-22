package com.example.elibrary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.elibrary.requests.RegisterDTO;
import com.example.elibrary.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    
    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
	private MockMvc mockMvc;

    @Test
    public void shoudReturnValidationError() throws Exception {
        
        RegisterDTO dto = new RegisterDTO();
        mockMvc.perform(post("/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dto))).andExpect(status().isBadRequest())
            .andDo(print());
    }

    @Test
    public void shoudReturnValidationUnauthorized() throws Exception {
        
        RegisterDTO dto = new RegisterDTO();
        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dto))).andExpect(status().isUnauthorized())
            .andDo(print());
    }
}
