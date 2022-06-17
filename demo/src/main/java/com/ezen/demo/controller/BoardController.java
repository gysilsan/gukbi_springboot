package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.demo.model.Board;
import com.ezen.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService svc;
	
	@GetMapping("/input")
	public String input(Model model) {
		Board b = new Board();
		model.addAttribute("board", b); //폼의 hidden 필드(pcode)에 0이 전달되도록 함
		return "board_inputform";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Map<String, Object> save(Board board, @SessionAttribute(name="uid", required = false) String uid) {
		Map<String, Object> map = new HashMap<>();
		
		if(uid == null) {
			map.put("saved", false);
			map.put("msg", "로그인 후에 사용할 수 있습니다.");
			return map;
		}
		
		boolean saved = svc.save(board);
		
		map.put("saved", saved);
		return map;
//		return "{\"title\":"+board.getTitle()+", "+"\"contents\":"+board.getContents()+"}";
	}
}