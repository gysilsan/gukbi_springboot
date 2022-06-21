package com.ezen.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.dao.JdbcBoardDao;
import com.ezen.demo.model.Board;

@Controller
@RequestMapping("/jdbcboard")
public class JdbcBoardController {
	@Autowired
	JdbcBoardDao dao;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board> list = dao.getList();
		model.addAttribute("list", list);
		return "/jdbcboard/boardList";
	}
	
	@GetMapping("/add_form")
	public String add_form() {
		return "/jdbcboard/add_form";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public String add(Board board) {
		boolean added = dao.add(board);
		return "{\"added\":"+added+"}";
	}
	
	@GetMapping("/detail/{num}")
	public String detail(@PathVariable("num") int num, Model model) {
		Board board = dao.getBoardByNum(num);
		model.addAttribute("board", board);
		return "/jdbcboard/detail";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam(name ="num") int num, Model model) {
		Board board = dao.getBoardByNum(num);
		model.addAttribute("board", board);
		return "/jdbcboard/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String update(Board board) {
		boolean updated = dao.update(board);
		return "{\"updated\":"+updated+"}";
	}
	
	@PostMapping("/delete/{num}")
	@ResponseBody
	public String delete(@PathVariable("num") int num) {
		boolean deleted = dao.delete(num);
		return "{\"deleted\":"+deleted+"}";
	}
}
