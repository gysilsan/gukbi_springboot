package com.ezen.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.model.Board;

@Controller
@RequestMapping("/board")
public class BoardController {

	@GetMapping("/input")
	public String input() {
		return "board_inputform";
	}
	
	@PostMapping("/res")
	@ResponseBody
	public String save(Board board) {
		System.out.println("title: " + board.getTitle());
		System.out.println("contents: " + board.getContents());
		return "{\"title\":"+board.getTitle()+", "+"\"contents\":"+board.getContents()+"}";
	}
}
