package net.slipp.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class HomeControllerTest {
	private MockMvc mockMvc;
	
	@Test
	public void home() throws Exception {
		standaloneSetup(new HomeController()).build()
			.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home"));
	}

}
