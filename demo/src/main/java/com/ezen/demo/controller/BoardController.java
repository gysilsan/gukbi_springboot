package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

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
	
	@PostMapping("/save")
	@ResponseBody
	public Map<String, Object> save(Board board) {
		System.out.println("title: " + board.getTitle());
		System.out.println("contents: " + board.getContents());
		
		Map<String, Object> map = new HashMap<>();
		map.put("title", board.getTitle());
		map.put("contents", board.getContents());
		
		return map;
//		return "{\"title\":"+board.getTitle()+", "+"\"contents\":"+board.getContents()+"}";
	}
}
