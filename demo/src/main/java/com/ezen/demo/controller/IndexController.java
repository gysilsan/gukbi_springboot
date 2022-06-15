package com.ezen.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "inde";
	}
	
	@GetMapping("/gugu")
	public String gugu() {
		return "gugu";
		
	}
}
