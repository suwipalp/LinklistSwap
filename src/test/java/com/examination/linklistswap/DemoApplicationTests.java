package com.examination.linklistswap;

import static org.assertj.core.api.Assertions.assertThat;

import com.examination.linklistswap.controller.LinklistSwapController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private LinklistSwapController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
