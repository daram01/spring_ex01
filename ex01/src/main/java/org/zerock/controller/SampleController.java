package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
// /sample(공통uri) /* 세부적인 것은 밑에 작성
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	// 세부적인 루트
	@RequestMapping("")
	public void basic() {
		log.info("basic~~~~~");
	}
	
	
	@GetMapping("/ex01")
	// http://localhost:8081/sample/ex01?name=hong&age=10
	public String ex01(SampleDTO dto) { // command 객체
		
		log.info("" + dto);
		
		return "ex01";
	}
}
