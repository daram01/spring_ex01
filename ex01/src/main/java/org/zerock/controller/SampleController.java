package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

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
	
	@GetMapping("ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
	}
	
	@GetMapping("ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		
		log.info("list dtos: " + list);
		
		return "ex02Bean";
	}
	
	@GetMapping("ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("Todo : " + todo);
		
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		// 커맨드 객체란? 클라이언트가 전달해주는 파라미터 데이터를 주입 받기 위해 사용되는 객체
		// 커맨드 객체(SampleDTO dto)의 특징
		// 1. 파라미터 자동으로 받기
		// 2. 뷰페이지로 커맨드 객체 정보도 같이 전달한다. 클래스의 첫 글자를 소문자로 구성하여 전달 (예> sampleDTO)

		// 기본 자료형
		// 1. 기본형 매개변수에 대해 뷰페이지로 값 전달을 위해 @ModelAttribute을 적용한다.
		// 2. Model객체에 담겨서 전달된다.

		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04"; // 뷰페이지
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge(10);
		
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		
		String msg ="{\"name\":\"홍길동\"}"; // {"name":"홍길동"}
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.............");
	}
	
	@PostMapping("exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("-----------------------------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
		});
	}
}
