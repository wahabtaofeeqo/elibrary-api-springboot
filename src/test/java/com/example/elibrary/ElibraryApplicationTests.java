package com.example.elibrary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.elibrary.controllers.IndexController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ElibraryApplicationTests {

	@Autowired
	private IndexController controller;

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		mockMvc.perform(get("/"))
			.andExpect(status().isOk()).andDo(print());
	}

}
