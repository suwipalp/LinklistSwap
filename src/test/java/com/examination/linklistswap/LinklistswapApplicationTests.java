package com.examination.linklistswap;

import java.util.Base64;

import com.examination.linklistswap.controller.LinklistSwapController;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LinklistSwapController.class)
class LinklistswapApplicationTests {

	private static final String ERR_MSG_LINKLIST_EMPTY = "linklistSwap.linklist: must not be empty";
	private static final String ERR_MSG_LINKLIST_INVAID_PATTERN = "linklistSwap.linklist: Input format is invalid (must be digit linked with '->', e.g. 1->2, 1->2->3)";

	@Autowired
	private MockMvc mockMvc;

	public static String base64Encode(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}

	@Test
	void getlinklistswap_Valid_1() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1->2->3->4"))).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString(base64Encode("2->1->4->3"))));
	}

	@Test
	void getlinklistswap_Valid_2() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("3->2->9->7->6"))).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString(base64Encode("2->3->7->9->6"))));
	}

	@Test
	void getlinklistswap_Valid_3() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1->100"))).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString(base64Encode("100->1"))));
	}

	@Test
	void getlinklistswap_Invalid_1() throws Exception {
		this.mockMvc.perform(get("/linklistswap")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(2)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_EMPTY)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_2() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode(""))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(2)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_EMPTY)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_3() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode(" "))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_4() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("->"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_5() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_6() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1->"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_7() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1->->"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_8() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1->->2"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_9() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("1=>2"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

	@Test
	void getlinklistswap_Invalid_10() throws Exception {
		this.mockMvc.perform(get("/linklistswap").param("linklist", base64Encode("a->b"))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is("Invalid input parameters")))
				.andExpect(jsonPath("$.errors").isArray()).andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", hasItem(ERR_MSG_LINKLIST_INVAID_PATTERN)));
	}

}
