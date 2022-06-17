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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.demo.model.User;
import com.ezen.demo.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("uid")
public class UserController {
	@Autowired
	UserService svc;
	
	@GetMapping("/login")
	public String login_form(Model model) {
		return "/user/login_form";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> login(User user, Model model) {
		boolean logined = svc.login(user);
		model.addAttribute("uid", user.getUid());
		Map<String, Object> map = new HashMap<>();
		map.put("logined", logined);
		return map;
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public Map<String, Object> logout(SessionStatus status) {
		status.setComplete();
		Map<String, Object> map = new HashMap<>();
		map.put("logout", true);
		return map;
	}
}
