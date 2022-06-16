package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.service.IndexService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired		// Field Injection
	private IndexService svc;
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("/gugu")
	@RequestMapping(value="/gugu", method=RequestMethod.GET)
	public String gugu2(@RequestParam(value="dan", required=false, defaultValue="2")int dan,
						Model model) {
//		IndexService svc = new IndexService(request);
		model.addAttribute("list", svc.getGugu(dan));
		return "gugu"; // /WEB-INF/jsp/*.jsp
	}
	
	@GetMapping("/gugu/{dan}")
	public String gugu3(@PathVariable("dan") int dan, Model model) {
//		IndexService svc = new IndexService(request);
		
		model.addAttribute("list", svc.getGugu(dan));
		return "gugu"; // /WEB-INF/jsp/*.jsp
	}
	
//	@PostMapping("/res")
//	@ResponseBody
//	public String gugu4() {
////		return "Hello";
//		return "{\"saved\":true}";
//	}
	
	@PostMapping("/res")
	@ResponseBody
	public Map<String,Object> gugu5() {
//		return "Hello";
		Map<String, Object> map = new HashMap<>();
		map.put("saved", true);
		return map;
	}
	
	@PostMapping("/res/dan/{num}") /*	/index/res/dan/7로 요청	*/
	@ResponseBody
	public Map<String,Object> gugu6(@PathVariable("num") int num) {
//		return "Hello";
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		return map;
	}
	
	@PostMapping("/res/fruit/sel")
	@ResponseBody
	public Map<String,Object> fruit(@RequestParam("fruit") String fruit) {
//		return "Hello";
		Map<String, Object> map = new HashMap<>();
		map.put("selection", fruit);
		return map;
	}
}