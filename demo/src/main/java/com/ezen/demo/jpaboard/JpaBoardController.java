package com.ezen.demo.jpaboard;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jpaboard")
public class JpaBoardController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JpaBoardService svc;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/list")
	private String list(Model model) {
		model.addAttribute("list", svc.findAll());
		return "/jpaboard/list";
	}
	
	@GetMapping("/insertForm")
	private String insert_form() {
		return "/jpaboard/insertForm";
	}
	
	@PostMapping("/insert")
	private String insert(Board board, Model model) {
		svc.insert(board);
		model.addAttribute("list", svc.findAll());
		return "/jpaboard/list";
	}
	
	@RequestMapping("/updateForm/{num}")
	private String updateForm(@PathVariable("num") int num, Model model) {
		Optional<Board> op = boardRepository.findById(num);
		Board board = op.get();
		model.addAttribute("board", board);
		return "/jpaboard/updateForm";
	}
	
	@RequestMapping("/update")
	private String update(Board board) {
		boardRepository.save(board);
		return "/jpaboard/detail/"+board.getNum();
	}
	
	@RequestMapping("/delete/{num}")
	private String delete(@PathVariable("num") int num) {
		Optional<Board> op = boardRepository.findById(num);
		
		if(op.isPresent()) {
			boardRepository.deleteById(num);
		}
			
		return "/jpaboard/list";
	}
	
	@RequestMapping("/detail/{num}")
	private String detail(@PathVariable("num") int num, Model model) {
		Optional<Board> op = boardRepository.findById(num);
		if(op.isPresent()) {
			model.addAttribute("board", op.get());
			return "/jpaboard/detail";
		}
		return "/jpaboard/list";
	}
	
}
