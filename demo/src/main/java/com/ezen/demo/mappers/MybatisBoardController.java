package com.ezen.demo.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.model.Board;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import oracle.net.aso.l;

@Controller
@RequestMapping("/mybatis/board")
public class MybatisBoardController {

	@Autowired
	private MybatisBoardMapper dao;
	
	@GetMapping("")
	public String index(Model model) {
		List<Board> list = dao.getList();
		model.addAttribute("list", list);
		return "/mybatisboard/boardList";
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board> list = dao.getList();
		model.addAttribute("list", list);
		return "/mybatisboard/boardList";
	}
	
	@GetMapping("/list/page/{page}")
	public String getListByPage(@PathVariable("page") int page, Model model) {
		PageHelper.startPage(page, 10);
		PageInfo<Board> pageInfo = new PageInfo<>(dao.getList());
		List<Board> list = pageInfo.getList();
		model.addAttribute("pageInfo", pageInfo);
		return "/mybatisboard/boardListByPage";
		
//		List<Board> list = dao.getList();
//		model.addAttribute("list", list);
//		return "/mybatisboard/boardList";
	}
	
//	@GetMapping("/getListByCategory")
//	public String getListByCategory(@RequestParam(name ="category") String category,
//					   @RequestParam(name ="key") String key,
//			Model model) {
//		List<Board> list = dao.getListByCategory(category, key);
//		model.addAttribute("list", list);
//		return "/mybatisboard/boardListByCategory";
//	}
	
	@PostMapping("/search")
	public String search(@RequestParam(name ="category") String cat,
								   @RequestParam(name ="key") String keyword,
								   @RequestParam(name="psize", defaultValue="5") int pageSize,
								   @RequestParam(name="page", defaultValue="1") int page,
								   Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("category", cat);
		map.put("keyword", keyword);
		
		PageHelper.startPage(page, pageSize);
		
		PageInfo<Board> pageInfo=new PageInfo<>(dao.search(map));
		model.addAttribute("category", cat);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageInfo", pageInfo);
		
		return "/mybatisboard/boardListByCategory";
		
	}
	
	@GetMapping("/add_form")
	public String add_form(@RequestParam(name ="pcode", required = false) Integer pcode, Model model) {
		Board b = new Board();
		if(pcode != null) b.setPcode(pcode);
		model.addAttribute("board", b);
		return "/mybatisboard/add_form";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public String add(Board board) {
		boolean added = dao.add(board)>0;
		return "{\"added\":"+added+"}";
	}
	
	@GetMapping("/detail/{num}")
	public String detail(@PathVariable("num") int num, Model model) {
		Board board = dao.getBoardByNum(num);
		model.addAttribute("board", board);
		return "/mybatisboard/detail";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam(name ="num") int num, Model model) {
		Board board = dao.getBoardByNum(num);
		model.addAttribute("board", board);
		return "/mybatisboard/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String update(Board board) {
		boolean updated = dao.update(board)>0;
		return "{\"updated\":"+updated+"}";
	}
	
	@PostMapping("/delete/{num}")
	@ResponseBody
	public String delete(@PathVariable("num") int num) {
		boolean deleted = dao.delete(num)>0;
		return "{\"deleted\":"+deleted+"}";
	}
	
	
}
